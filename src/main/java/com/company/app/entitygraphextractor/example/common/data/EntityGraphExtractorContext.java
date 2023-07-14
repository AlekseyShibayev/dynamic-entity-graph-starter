package com.company.app.entitygraphextractor.example.common.data;

import java.util.List;

public interface EntityGraphExtractorContext<E> {

    Class<E> getClass_();

    Long getId_();

    List<Node> getNodes_();

    E extract();

}
