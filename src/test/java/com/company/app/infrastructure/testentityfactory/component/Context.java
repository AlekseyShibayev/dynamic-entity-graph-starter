package com.company.app.infrastructure.testentityfactory.component;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.repository.FirstInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.domain.repository.FourthRepository;
import com.company.app.entitygraphextractor.domain.repository.SecondRepository;
import com.company.app.entitygraphextractor.domain.repository.ThirdRepository;
import com.company.app.infrastructure.testentityfactory.component.impl.FirstInfoCreateAction;
import com.company.app.infrastructure.testentityfactory.component.impl.SecondCreateAction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Context {

    private First first;
    private List<CreateAction> actionsList;
    private int amount;

    private TestEntityFactoryFinisher testEntityFactoryFinisher;
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

    public Context with(CreateAction firstActions) {
        this.actionsList.add(firstActions);
        return this;
    }

    public Context withFirstInfo(String description) {
        this.actionsList.add(FirstInfoCreateAction.of(description));
        return this;
    }

    public Context withSecond(String name) {
        this.actionsList.add(SecondCreateAction.of(name));
        return this;
    }

}
