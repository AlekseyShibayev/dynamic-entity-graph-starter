package com.company.app.entitygraphextractor.example.common;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityGraphExtractorAbstractContext<E> implements EntityGraphExtractorContext<E> {

    protected List<E> entities;
    protected EntityGraphExtractorFinisher finisher;
    protected final List<EntityGraphExtractorNode> nodes = new ArrayList<>();

    @Override
    public E extractOne() {
        return finisher.extractOne(this);
    }

    @Override
    public List<E> extractAll() {
        return finisher.extractAll(this);
    }

    @Override
    public List<E> getEntities_() {
        return entities;
    }

    @Override
    public List<EntityGraphExtractorNode> getNodes_() {
        return nodes;
    }

    protected void addParameter(String first) {
        nodes.add(EntityGraphExtractorNode.builder()
                .name(first)
                .build());
    }

    protected void addParameter(String first, String second) {
        nodes.add(EntityGraphExtractorNode.builder()
                .name(first)
                .child(EntityGraphExtractorNode.builder()
                        .name(second)
                        .build())
                .build());
    }

    protected void addParameter(String first, String second, String third) {
        nodes.add(EntityGraphExtractorNode.builder()
                .name(first)
                .child(EntityGraphExtractorNode.builder()
                        .name(second)
                        .child(EntityGraphExtractorNode.builder()
                                .name(third)
                                .build())
                        .build())
                .build());
    }

}
