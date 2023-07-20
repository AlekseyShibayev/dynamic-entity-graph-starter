package com.company.app.infrastructure.testentityfactory.component.impl;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.entity.Second;
import com.company.app.entitygraphextractor.domain.entity.Third;
import com.company.app.entitygraphextractor.domain.entity.ThirdInfo;
import com.company.app.infrastructure.testentityfactory.component.CreateAction;
import com.company.app.infrastructure.testentityfactory.component.FirstContext;
import com.company.app.infrastructure.testentityfactory.component.TestEntityFactoryRegistry;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ThirdInfoCreateAction implements CreateAction {

    private String description;

    @Override
    public void doEnrich(FirstContext context) {
        First first = context.getFirst();
        TestEntityFactoryRegistry testEntityFactoryRegistry = context.getTestEntityFactoryRegistry();

        List<Second> seconds = first.getSeconds();
        for (Second second : seconds) {
            List<Third> thirds = second.getThirds();
            for (Third third : thirds) {
                ThirdInfo thirdInfo = testEntityFactoryRegistry.createThirdInfo(third, description);
                third.setThirdInfo(thirdInfo);
            }
            context.getThirdRepository().saveAll(thirds);
        }

        first.setSeconds(seconds);
        context.getFirstRepository().save(first);
    }

    public static ThirdInfoCreateAction of(String description) {
        return ThirdInfoCreateAction.builder().description(description).build();
    }

}
