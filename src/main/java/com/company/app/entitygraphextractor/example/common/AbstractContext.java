package com.company.app.entitygraphextractor.example.common;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContext<E> implements Context<E> {

    @Setter
    private EntityGraphExtractorHandler mainHandler;

    protected final List<Node> nodes = new ArrayList<>();

    @Override
    public E extract() {
        return mainHandler.extract(this);
    }

    @Override
    public List<Node> getNodes_() {
        return nodes;
    }

}
