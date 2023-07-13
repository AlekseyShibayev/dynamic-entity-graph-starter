package com.company.app.example.component;


import com.company.app.domain.entity.Chat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.Collections;

@Slf4j
@Repository
public class ChatEntityGraphExtractorHandler {

    @Autowired
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Chat extract(ChatEntityGraphExtractorContext context) {
        EntityGraph<Chat> entityGraph = entityManager.createEntityGraph(Chat.class);

        prepareGraph(context, entityGraph);
        log.debug("[{}]: try extract with [{}] parameters", context.getId(), context.getParameters().size());

        return entityManager.find(Chat.class,
                context.getId(),
                Collections.singletonMap("javax.persistence.loadgraph", entityGraph));
    }

    private void prepareGraph(ChatEntityGraphExtractorContext context, EntityGraph<Chat> entityGraph) {
        for (ChatEntityGraphExtractorNode node : context.getParameters()) {
            entityGraph.addAttributeNodes(node.getNodeName());
            if (node.getChild() != null) {
                ChatEntityGraphExtractorNode nodeChild = node.getChild();
                entityGraph.addSubgraph(node.getNodeName())
                        .addAttributeNodes(nodeChild.getNodeName());
            }
        }
    }

}
