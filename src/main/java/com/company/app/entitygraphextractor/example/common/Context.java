package com.company.app.entitygraphextractor.example.common;

import java.util.List;

interface Context<E> {

    Class<E> getClass_();

    Long getId_();

    List<Node> getNodes_();

    E extract();

}
