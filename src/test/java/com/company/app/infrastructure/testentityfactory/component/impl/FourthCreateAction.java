package com.company.app.infrastructure.testentityfactory.component.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.entity.Fourth;
import com.company.app.entitygraphextractor.domain.entity.Second;
import com.company.app.entitygraphextractor.domain.entity.Third;
import com.company.app.infrastructure.testentityfactory.component.CreateAction;
import com.company.app.infrastructure.testentityfactory.component.FirstContext;
import com.company.app.infrastructure.testentityfactory.component.TestEntityFactoryRegistry;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FourthCreateAction implements CreateAction {

    private String name;
    private int amount;

    @Override
    public void doEnrich(FirstContext context) {
        First first = context.getFirst();
        List<Second> secondList = first.getSeconds();
        TestEntityFactoryRegistry testEntityFactoryRegistry = context.getTestEntityFactoryRegistry();

        for (Second second : secondList) {
            List<Third> thirds = second.getThirds();
            for (Third third : thirds) {
                List<Fourth> fourth = testEntityFactoryRegistry.createFourth(third, name, amount);
                third.setFourths(fourth);
            }
        }

        context.getFirstRepository().save(first);
    }


    public static FourthCreateAction of(String name) {
        return FourthCreateAction.of(name, 1);
    }

    public static FourthCreateAction of(String name, int amount) {
        return FourthCreateAction.builder()
                .name(name)
                .amount(amount)
                .build();
    }

}
