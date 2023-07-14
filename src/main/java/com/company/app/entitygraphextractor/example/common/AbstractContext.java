package com.company.app.entitygraphextractor.example.common;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContext<E> implements Context<E> {

    protected E entity;
    @Override
    public void setEntity_(E entity) {
        this.entity = entity;
    }


    protected final List<Node> nodes = new ArrayList<>();
    @Override
    public List<Node> getNodes_() {
        return nodes;
    }


    @Autowired
    private EntityGraphExtractorHandler mainHandler;
    @Override
    public E extract() {
        return mainHandler.extract(this);
    }

}
