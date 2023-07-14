package com.company.app.entitygraphextractor.example.common.component;

import com.company.app.entitygraphextractor.example.common.data.EntityGraphExtractorContext;
import com.company.app.entitygraphextractor.example.common.data.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Slf4j
@Repository
public class EntityGraphExtractorHandler {

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public <E> E extract(EntityGraphExtractorContext<E> context) {
        Long id = context.getId_();
        Class<E> eClass = context.getClass_();
        List<Node> nodes = context.getNodes_();

        EntityGraph<E> entityGraph = entityManager.createEntityGraph(eClass);

        for (Node node : nodes) {
            entityGraph.addAttributeNodes(node.getName());
            if (node.getChild() != null) {
                Node nodeChild = node.getChild();
                entityGraph.addSubgraph(node.getName())
                        .addAttributeNodes(nodeChild.getName());
            }
        }

        log.debug("[{}]: try extract with [{}] parameters", id, nodes.size());

        return entityManager.find(eClass,
                id,
                Collections.singletonMap("javax.persistence.loadgraph", entityGraph));
    }

}
