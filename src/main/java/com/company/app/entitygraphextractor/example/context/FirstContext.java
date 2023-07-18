package com.company.app.entitygraphextractor.example.context;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.example.common.AbstractContext;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorFinisher;

public class FirstContext extends AbstractContext<First> {

    private FirstContext(First first, EntityGraphExtractorFinisher finisher) {
        this.entity = first;
        this.finisher = finisher;
    }

    public static FirstContext of(First first, EntityGraphExtractorFinisher finisher) {
        return new FirstContext(first, finisher);
    }

    @Override
    public Class<First> getClass_() {
        return First.class;
    }

    @Override
    public Long getId_() {
        return entity.getId();
    }

    public FirstContext withFirstInfo() {
        addParameter("firstInfo");
        return this;
    }

    public FirstContext withSeconds() {
        addParameter("seconds");
        return this;
    }

}
