package com.company.app.infrastructure.testentityfactory.component.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.entity.Second;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.domain.repository.SecondRepository;
import com.company.app.infrastructure.testentityfactory.component.CreateAction;
import com.company.app.infrastructure.testentityfactory.component.FirstContext;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SecondCreateAction implements CreateAction {

    private String name;
    private int amount;

    @Override
    public void doEnrich(FirstContext context) {
        First first = context.getFirst();
        FirstRepository firstRepository = context.getFirstRepository();
        SecondRepository secondRepository = context.getSecondRepository();

        List<Second> secondList = get(first);
        secondRepository.saveAll( secondList);

        List<Second> seconds = first.getSeconds();
        seconds.addAll(secondList);
        firstRepository.save(first);
    }

    private List<Second> get(First first) {
        List<Second> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Second second = Second.builder()
                    .name(name + "_" + i)
                    .first(first)
                    .build();
            result.add(second);
        }
        return result;
    }

    public static SecondCreateAction of(String name) {
        return SecondCreateAction.of(name, 1);
    }
    public static SecondCreateAction of(String name, int amount) {
        return SecondCreateAction.builder()
                .name(name)
                .amount(amount)
                .build();
    }

}
