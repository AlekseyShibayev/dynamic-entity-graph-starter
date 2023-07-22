package com.company.app.entitygraphextractor.example.common;

import lombok.experimental.UtilityClass;

import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import java.util.List;

@UtilityClass
public class EntityGraphExtractorGraphPreparer {

    public static EntityGraphExtractorNode createHead(List<EntityGraphExtractorNode> nodes) {
        EntityGraphExtractorNode head = new EntityGraphExtractorNode();
        head.setName("head");

        for (EntityGraphExtractorNode node : nodes) {
            recursionCreate(head, node);
        }

        return head;
    }

    private static void recursionCreate(EntityGraphExtractorNode parent, EntityGraphExtractorNode child) {
        List<EntityGraphExtractorNode> nodeList = parent.getNodeList();
        if (isNotContains(nodeList, child)) {
            nodeList.add(child);
        } else {

            for (EntityGraphExtractorNode node : nodeList) {
                if (node.getName().equals(child.getName())) {
                    child.setNodeList(node.getNodeList());
                }
            }

        }
        if (child.getChild() != null) {
            recursionCreate(child, child.getChild());
        }
    }

    private static boolean isNotContains(List<EntityGraphExtractorNode> parentNodeList, EntityGraphExtractorNode child) {
        for (EntityGraphExtractorNode node : parentNodeList) {
            if (node.getName().equals(child.getName())) {
                return false;
            }
        }
        return true;
    }

    public static <E> void fillGraph(EntityGraph<E> entityGraph, EntityGraphExtractorNode head) {
        List<EntityGraphExtractorNode> headNodeList = head.getNodeList();
        for (EntityGraphExtractorNode node : headNodeList) {
            if (node.getNodeList().isEmpty()) {
                entityGraph.addAttributeNodes(node.getName());
            } else {
                Subgraph<E> subgraph = entityGraph.addSubgraph(node.getName());
                recursionFill(subgraph, node);
            }
        }
    }

    private static <E> void recursionFill(Subgraph<E> subgraph, EntityGraphExtractorNode node) {
        List<EntityGraphExtractorNode> nodeList = node.getNodeList();
        if (nodeList.isEmpty()) {
            subgraph.addAttributeNodes(node.getName()); // выход из рекурсии
        } else {
            for (EntityGraphExtractorNode child : nodeList) {
                recursionFill(subgraph.addSubgraph(node.getName()), child);
            }
        }
    }

}
