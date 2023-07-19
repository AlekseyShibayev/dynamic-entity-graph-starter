package com.company.app.entitygraphextractor.example.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class EntityGraphExtractorFinisher {

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public <E> E extractOne(Context<E> context) {
        Long id = context.getId_();
        Class<E> eClass = context.getClass_();
        List<Node> nodes = context.getNodes_();

        EntityGraph<E> entityGraph = entityManager.createEntityGraph(eClass);
        prepareGraph(nodes, entityGraph);

        return entityManager.find(eClass,
                id,
                Collections.singletonMap("javax.persistence.loadgraph", entityGraph));
    }

    @Transactional(readOnly = true)
    public <E> List<E> extractAll(Context<E> context) {
        List<E> entities = context.getEntities_();
        Class<E> eClass = context.getClass_();
        List<Node> nodes = context.getNodes_();


        Set<Long> ids = new HashSet<>();
        for (E entity : entities) {
            Long id = context.getId_(entity);
            ids.add(id);
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(eClass);
        Root<E> eRoot = criteriaQuery.from(eClass);

        criteriaQuery.select(eRoot)
                .where(eRoot.get("id")
                        .in(ids));

        EntityGraph<E> entityGraph = entityManager.createEntityGraph(eClass);
        prepareGraph(nodes, entityGraph);

        TypedQuery<E> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", entityGraph);
        return typedQuery.getResultList();
    }

    private <E> void prepareGraph(List<Node> nodes, EntityGraph<E> entityGraph) {
        for (Node node : nodes) {
            entityGraph.addAttributeNodes(node.getName());
            if (node.getChild() != null) {
                Node nodeChild = node.getChild();
                entityGraph.addSubgraph(node.getName())
                        .addAttributeNodes(nodeChild.getName());
            }
        }
    }

}
