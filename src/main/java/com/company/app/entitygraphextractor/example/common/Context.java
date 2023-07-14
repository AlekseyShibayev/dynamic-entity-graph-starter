package com.company.app.entitygraphextractor.example.common;

import java.util.List;

public interface Context<E> {

    Class<E> getClass_();

    Long getId_();

    List<Node> getNodes_();

    void setEntity_(E e);

    E extract();

}
