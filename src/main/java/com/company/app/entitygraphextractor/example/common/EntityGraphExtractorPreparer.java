package com.company.app.entitygraphextractor.example.common;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EntityGraphExtractorPreparer {

    public <E>EntityGraph<E> getPreparedEntityGraph(EntityGraphExtractorContext<E> context, EntityManager em) {
        Class<E> eClass = context.getClass_();
        List<EntityGraphExtractorNode> nodes = context.getNodes_();

        EntityGraph<E> entityGraph = em.createEntityGraph(eClass);
        prepareGraph(nodes, entityGraph);

        return entityGraph;
    }

    private <E> void prepareGraph(List<EntityGraphExtractorNode> nodes, EntityGraph<E> entityGraph) {
        for (EntityGraphExtractorNode node : nodes) {
            entityGraph.addAttributeNodes(node.getName());
            if (node.getChild() != null) {
                EntityGraphExtractorNode nodeChild = node.getChild();
                entityGraph.addSubgraph(node.getName())
                        .addAttributeNodes(nodeChild.getName());
            }
        }
    }

}
