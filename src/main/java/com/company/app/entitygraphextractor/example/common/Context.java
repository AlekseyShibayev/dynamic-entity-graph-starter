package com.company.app.entitygraphextractor.example.common;

import java.util.List;

interface Context<E> {

    Class<E> getClass_();

    Long getId_();

    Long getId_(E e);

    List<E> getEntities_();

    List<Node> getNodes_();

    E extractOne();

    List<E> extractAll();

}
