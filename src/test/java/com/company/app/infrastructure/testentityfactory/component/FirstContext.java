package com.company.app.infrastructure.testentityfactory.component;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.repository.FirstInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.domain.repository.FourthRepository;
import com.company.app.entitygraphextractor.domain.repository.SecondRepository;
import com.company.app.entitygraphextractor.domain.repository.ThirdRepository;
import com.company.app.infrastructure.testentityfactory.component.impl.FirstInfoCreateAction;
import com.company.app.infrastructure.testentityfactory.component.impl.FourthCreateAction;
import com.company.app.infrastructure.testentityfactory.component.impl.SecondCreateAction;
import com.company.app.infrastructure.testentityfactory.component.impl.SecondInfoCreateAction;
import com.company.app.infrastructure.testentityfactory.component.impl.ThirdCreateAction;
import com.company.app.infrastructure.testentityfactory.component.impl.ThirdInfoCreateAction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FirstContext {

    private First first;
    private List<CreateAction> actionsList;
    private int amount;

    private TestEntityFactoryFinisher testEntityFactoryFinisher;
    private TestEntityFactoryRegistry testEntityFactoryRegistry;
    private FirstRepository firstRepository;
    private FirstInfoRepository firstInfoRepository;
    private SecondRepository secondRepository;
    private ThirdRepository thirdRepository;
    private FourthRepository fourthRepository;

    public First createOne() {
        this.amount = 1;
        return testEntityFactoryFinisher.create(this).get(0);
    }

    public List<First> createMany(int amount) {
        this.amount = amount;
        return testEntityFactoryFinisher.create(this);
    }

    public FirstContext with(CreateAction firstActions) {
        this.actionsList.add(firstActions);
        return this;
    }

    public FirstContext withFirstInfo(String description) {
        this.actionsList.add(FirstInfoCreateAction.of(description));
        return this;
    }

    public FirstContext withSecond(String name) {
        this.actionsList.add(SecondCreateAction.of(name));
        return this;
    }

    public FirstContext withSecond(String name, int amount) {
        this.actionsList.add(SecondCreateAction.of(name, amount));
        return this;
    }

    public FirstContext withSecondInfo(String description) {
        this.actionsList.add(SecondInfoCreateAction.of(description));
        return this;
    }

    public FirstContext withThird(String name, int amount) {
        this.actionsList.add(ThirdCreateAction.of(name, amount));
        return this;
    }

    public FirstContext withThirdInfo(String description) {
        this.actionsList.add(ThirdInfoCreateAction.of(description));
        return this;
    }

    public FirstContext withFourth(String name, int amount) {
        this.actionsList.add(FourthCreateAction.of(name, amount));
        return this;
    }

}
