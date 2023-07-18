package com.company.app.infrastructure.testentityfactory.component.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.entity.Second;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.domain.repository.SecondRepository;
import com.company.app.infrastructure.testentityfactory.component.Context;
import com.company.app.infrastructure.testentityfactory.component.CreateAction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SecondCreateAction implements CreateAction {

    private String name;

    @Override
    public void doEnrich(Context context) {
        First first = context.getFirst();
        FirstRepository firstRepository = context.getFirstRepository();
        SecondRepository secondRepository = context.getSecondRepository();

        Second second = Second.builder()
                .name(name)
                .first(first)
                .build();
        secondRepository.save(second);


        List<Second> seconds = first.getSeconds();
        seconds.add(second);
        firstRepository.save(first);
    }

    public static SecondCreateAction of(String name) {
        return SecondCreateAction.builder().name(name).build();
    }

}
