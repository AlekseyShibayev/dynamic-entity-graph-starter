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
        EntityGraphExtractorNode firstNode = new EntityGraphExtractorNode();
        firstNode.setName(first);
        nodes.add(firstNode);
    }

    protected void addParameter(String first, String second) {
        EntityGraphExtractorNode firstNode = new EntityGraphExtractorNode();
        firstNode.setName(first);

        EntityGraphExtractorNode secondNode = new EntityGraphExtractorNode();
        secondNode.setName(second);
        firstNode.setChild(secondNode);

        nodes.add(firstNode);
    }

    protected void addParameter(String first, String second, String third) {
        EntityGraphExtractorNode firstNode = new EntityGraphExtractorNode();
        firstNode.setName(first);

        EntityGraphExtractorNode secondNode = new EntityGraphExtractorNode();
        secondNode.setName(second);
        firstNode.setChild(secondNode);

        EntityGraphExtractorNode thirdNode = new EntityGraphExtractorNode();
        thirdNode.setName(third);
        secondNode.setChild(thirdNode);

        nodes.add(firstNode);
    }

}
