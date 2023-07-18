package com.company.app.infrastructure.testentityfactory.component.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.entity.FirstInfo;
import com.company.app.entitygraphextractor.domain.repository.FirstInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.infrastructure.testentityfactory.component.Context;
import com.company.app.infrastructure.testentityfactory.component.CreateAction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FirstInfoCreateAction implements CreateAction {

    private String description;

    @Override
    public void doEnrich(Context context) {
        First first = context.getFirst();
        FirstRepository firstRepository = context.getFirstRepository();
        FirstInfoRepository firstInfoRepository = context.getFirstInfoRepository();

        FirstInfo aDefault = FirstInfo.builder()
                .first(first)
                .description(description)
                .build();
        firstInfoRepository.save(aDefault);

        first.setFirstInfo(aDefault);
        firstRepository.save(first);
    }

    public static FirstInfoCreateAction of(String description) {
        return FirstInfoCreateAction.builder().description(description).build();
    }

}
