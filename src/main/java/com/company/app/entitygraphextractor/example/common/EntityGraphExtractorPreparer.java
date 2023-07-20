package com.company.app.entitygraphextractor.example.common;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class EntityGraphExtractorPreparer {

    public <E> EntityGraph<E> getEntityGraph(EntityGraphExtractorContext<E> context, EntityManager em) {
        Class<E> eClass = context.getClass_();
        List<EntityGraphExtractorNode> nodes = context.getNodes_();

        EntityGraph<E> entityGraph = em.createEntityGraph(eClass);
        prepareGraph(nodes, entityGraph);

        return entityGraph;
    }

    private <E> void prepareGraph(List<EntityGraphExtractorNode> nodes, EntityGraph<E> entityGraph) {

//        Map<String, List<EntityGraphExtractorNode>> group = new HashMap<>();
//        for (EntityGraphExtractorNode node : nodes) {
//            String name = node.getName();
//            if (group.containsKey(name)) {
//                List<EntityGraphExtractorNode> entityGraphExtractorNodes = group.get(name);
//                entityGraphExtractorNodes.add(node);
//            } else {
//                group.put(name, Collections.singletonList(node));
//            }
//        }
//
//        for (Map.Entry<String, List<EntityGraphExtractorNode>> entry : group.entrySet()) {
//            String key = entry.getKey();
//            List<EntityGraphExtractorNode> value = entry.getValue();
//
//            entityGraph.addAttributeNodes(node.getName());
//        }


        EntityGraphExtractorNode head = getHead(nodes);


        for (EntityGraphExtractorNode node : nodes) {
            entityGraph.addAttributeNodes(node.getName());

            if (node.getChild() != null) {
                EntityGraphExtractorNode nodeChild = node.getChild();
                entityGraph.addSubgraph(node.getName())
                        .addAttributeNodes(nodeChild.getName());

                if (nodeChild.getChild() != null) {
                    EntityGraphExtractorNode nodeChild2 = nodeChild.getChild();
                    entityGraph.addSubgraph(node.getName())
                            .addSubgraph(nodeChild.getName())
                            .addAttributeNodes(nodeChild2.getName());
                }
            }
        }
    }

    private EntityGraphExtractorNode getHead(List<EntityGraphExtractorNode> nodes) {
        // todo строим дерево
        EntityGraphExtractorNode head = EntityGraphExtractorNode.builder()
                .name("HEAD")
                .nodeList(new ArrayList<>())
                .build();

        for (EntityGraphExtractorNode node : nodes) {

            recursion(head, node);

        }


        return head;
    }

    private void recursion(EntityGraphExtractorNode parent, EntityGraphExtractorNode child) {
        List<EntityGraphExtractorNode> nodeList = parent.getNodeList();
        for (EntityGraphExtractorNode node : nodeList) {
            if (node.getName().equals(child.getName())) {
                // значит он там уже был и
                if (child.getChild() != null) {
                    recursion(child, child.getChild());
                }
            }
        }

        nodeList.add(child);
        if (child.getChild() != null) {
            recursion(child, child.getChild());
        }
    }

    public <E> String getFieldNameWithId(EntityGraphExtractorContext<E> context) {
        List<E> entities = context.getEntities_();
        E entity = entities.get(0);
        Class<?> aClass = entity.getClass();
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(this::isIdAnnotation)
                .map(Field::getName)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("can't find field with @Id"));
    }

    private boolean isIdAnnotation(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .anyMatch(declaredAnnotation -> declaredAnnotation.annotationType().equals(Id.class));
    }

    public <E> Set<Long> getIds(EntityGraphExtractorContext<E> context) {
        return context.getEntities_().stream()
                .map(context::getId_)
                .collect(Collectors.toSet());
    }

}
