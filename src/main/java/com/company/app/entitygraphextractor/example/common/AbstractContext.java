package com.company.app.entitygraphextractor.example.common;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContext<E> implements Context<E> {

    protected E entity;
    protected List<E> entities;
    protected EntityGraphExtractorFinisher finisher;
    protected final List<Node> nodes = new ArrayList<>();

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
    public List<Node> getNodes_() {
        return nodes;
    }

    protected void addParameter(String first) {
        nodes.add(Node.builder()
                .name(first)
                .build());
    }

    protected void addParameter(String first, String second) {
        nodes.add(Node.builder()
                .name(first)
                .child(Node.builder()
                        .name(second)
                        .build())
                .build());
    }

    protected void addParameter(String first, String second, String third) {
        nodes.add(Node.builder()
                .name(first)
                .child(Node.builder()
                        .name(second)
                        .child(Node.builder()
                                .name(third)
                                .build())
                        .build())
                .build());
    }

}
