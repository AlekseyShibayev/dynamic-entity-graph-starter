package com.company.app.infrastructure.testentityfactory.component.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
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
public class ThirdCreateAction implements CreateAction {

    private String name;
    private int amount;

    @Override
    public void doEnrich(FirstContext context) {
        First first = context.getFirst();
        List<Second> secondList = first.getSeconds();
        TestEntityFactoryRegistry testEntityFactoryRegistry = context.getTestEntityFactoryRegistry();

        for (Second second : secondList) {
            List<Third> thirds = testEntityFactoryRegistry.createThirds(second, name, amount);
            second.setThirds(thirds);
        }
        context.getSecondRepository().saveAll(secondList);
    }


    public static ThirdCreateAction of(String name) {
        return ThirdCreateAction.of(name, 1);
    }

    public static ThirdCreateAction of(String name, int amount) {
        return ThirdCreateAction.builder()
                .name(name)
                .amount(amount)
                .build();
    }

}
