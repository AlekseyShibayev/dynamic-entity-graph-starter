package com.company.app.entitygraphextractor.example.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EntityGraphExtractorFinisher {

    private final EntityManager entityManager;
    private final EntityGraphExtractorPreparer entityGraphExtractorPreparer;

    @Transactional(readOnly = true)
    public <E> E extractOne(EntityGraphExtractorContext<E> context) {
        List<E> entities = context.getEntities_();
        Class<E> eClass = context.getClass_();

        EntityGraph<E> preparedEntityGraph = entityGraphExtractorPreparer.getPreparedEntityGraph(context, entityManager);

        return entityManager.find(eClass,
                context.getId_(entities.get(0)),
                Collections.singletonMap("javax.persistence.loadgraph", preparedEntityGraph));
    }

    @Transactional(readOnly = true)
    public <E> List<E> extractAll(EntityGraphExtractorContext<E> context) {
        Class<E> eClass = context.getClass_();

        Set<Long> ids = getIds(context);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(eClass);
        Root<E> eRoot = criteriaQuery.from(eClass);
        criteriaQuery.select(eRoot)
                .where(eRoot.get("id") //todo поковырять рефлекшеном
                        .in(ids));

        EntityGraph<E> preparedEntityGraph = entityGraphExtractorPreparer.getPreparedEntityGraph(context, entityManager);

        TypedQuery<E> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setHint("javax.persistence.loadgraph", preparedEntityGraph);
        return typedQuery.getResultList();
    }

    private <E> Set<Long> getIds(EntityGraphExtractorContext<E> context) {
        Set<Long> ids = new HashSet<>();
        for (E entity : context.getEntities_()) {
            Long id = context.getId_(entity);
            ids.add(id);
        }
        return ids;
    }

}
