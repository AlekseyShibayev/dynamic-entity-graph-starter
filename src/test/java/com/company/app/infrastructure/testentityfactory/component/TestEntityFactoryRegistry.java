package com.company.app.infrastructure.testentityfactory.component;

import com.company.app.entitygraphextractor.domain.repository.FirstInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.domain.repository.FourthRepository;
import com.company.app.entitygraphextractor.domain.repository.SecondRepository;
import com.company.app.entitygraphextractor.domain.repository.ThirdRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestEntityFactoryRegistry {

    private final TestEntityFactoryFinisher testEntityFactoryFinisher;
    private final FirstRepository firstRepository;
    private final FirstInfoRepository firstInfoRepository;
    private final SecondRepository secondRepository;
    private final ThirdRepository thirdRepository;
    private final FourthRepository fourthRepository;

    public Context getContext() {
        return Context.builder()
                .actionsList(Lists.newArrayList())

                .testEntityFactoryFinisher(testEntityFactoryFinisher)

                .firstRepository(firstRepository)
                .firstInfoRepository(firstInfoRepository)
                .secondRepository(secondRepository)
                .thirdRepository(thirdRepository)
                .fourthRepository(fourthRepository)
                .build();
    }

}
