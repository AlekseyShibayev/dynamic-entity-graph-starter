package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.example.common.data.EntityGraphExtractorContext;

@FunctionalInterface
public interface Context<Entity> {

    <E> EntityGraphExtractorContext<E> of(Entity context);

}
