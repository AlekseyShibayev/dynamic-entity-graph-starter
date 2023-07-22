package com.company.app.entitygraphextractor.example.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorAbstractContext;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorFinisher;

import java.util.List;

public class FirstContext extends EntityGraphExtractorAbstractContext<First> {

    private FirstContext(List<First> firsts, EntityGraphExtractorFinisher finisher) {
        this.entities = firsts;
        this.finisher = finisher;
    }

    public static FirstContext of(List<First> firsts, EntityGraphExtractorFinisher finisher) {
        return new FirstContext(firsts, finisher);
    }

    @Override
    public Class<First> getClass_() {
        return First.class;
    }

    @Override
    public Long getId_(First first) {
        return first.getId();
    }

    public FirstContext withFirstInfo() {
        addParameter("firstInfo");
        return this;
    }

    public FirstContext withSeconds() {
        addParameter("seconds");
        return this;
    }

    public FirstContext withSecondsAndSecondInfo() {
        addParameter("seconds", "secondInfo");
        return this;
    }

    public FirstContext withSecondsAndThirds() {
        addParameter("seconds", "thirds");
        return this;
    }

    public FirstContext withSecondsAndThirdsAndThirdInfo() {
        addParameter("seconds", "thirds", "thirdInfo");
        return this;
    }

    public FirstContext withSecondsAndThirdsAndFourths() {
        addParameter("seconds", "thirds", "fourths");
        return this;
    }

}
