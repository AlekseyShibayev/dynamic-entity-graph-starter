package com.company.app.infrastructure.testentityfactory.component.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.entity.Second;
import com.company.app.entitygraphextractor.domain.entity.SecondInfo;
import com.company.app.infrastructure.testentityfactory.component.CreateAction;
import com.company.app.infrastructure.testentityfactory.component.FirstContext;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SecondInfoCreateAction implements CreateAction {

    private String description;

    @Override
    public void doEnrich(FirstContext context) {
        First first = context.getFirst();

        List<Second> seconds = first.getSeconds();
        seconds.forEach(second -> second.setSecondInfo(SecondInfo.builder().description(description).second(second).build()));

        first.setSeconds(seconds);
        context.getFirstRepository().save(first);
    }

    public static SecondInfoCreateAction of(String description) {
        return SecondInfoCreateAction.builder().description(description).build();
    }

}
