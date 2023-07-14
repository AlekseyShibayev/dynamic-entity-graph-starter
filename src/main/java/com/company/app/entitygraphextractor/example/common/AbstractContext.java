package com.company.app.entitygraphextractor.example.common;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContext<E> implements Context<E> {

    protected E entity;
    protected EntityGraphExtractorHandler handler;

    protected final List<Node> nodes = new ArrayList<>();

    @Override
    public E extract() {
        return handler.extract(this);
    }

    @Override
    public List<Node> getNodes_() {
        return nodes;
    }

}
