package com.company.app.infrastructure.testentityfactory.component;

import com.company.app.entitygraphextractor.domain.entity.Fourth;
import com.company.app.entitygraphextractor.domain.entity.Second;
import com.company.app.entitygraphextractor.domain.entity.Third;
import com.company.app.entitygraphextractor.domain.entity.ThirdInfo;
import com.company.app.entitygraphextractor.domain.repository.FirstInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.domain.repository.FourthRepository;
import com.company.app.entitygraphextractor.domain.repository.SecondRepository;
import com.company.app.entitygraphextractor.domain.repository.ThirdInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.ThirdRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestEntityFactoryRegistry {

    private final TestEntityFactoryFinisher testEntityFactoryFinisher;
    private final FirstRepository firstRepository;
    private final FirstInfoRepository firstInfoRepository;
    private final SecondRepository secondRepository;
    private final ThirdRepository thirdRepository;
    private final ThirdInfoRepository thirdInfoRepository;
    private final FourthRepository fourthRepository;

    public FirstContext getFirstContext() {
        return FirstContext.builder()
                .actionsList(Lists.newArrayList())

                .testEntityFactoryFinisher(testEntityFactoryFinisher)
                .testEntityFactoryRegistry(this)

                .firstRepository(firstRepository)
                .firstInfoRepository(firstInfoRepository)
                .secondRepository(secondRepository)
                .thirdRepository(thirdRepository)
                .fourthRepository(fourthRepository)
                .build();
    }

    public List<Third> createThirds(Second second, String name, int amount) {
        List<Third> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Third third = Third.builder()
                    .name(name + "_" + i)
                    .second(second)
                    .build();
            result.add(third);
        }
        return thirdRepository.saveAll(result);
    }

    public ThirdInfo createThirdInfo(Third third, String description) {
        ThirdInfo thirdInfo = ThirdInfo.builder()
                .description(description)
                .third(third)
                .build();
        return thirdInfoRepository.save(thirdInfo);
    }

    public List<Fourth> createFourth(Third third, String name, int amount) {
        List<Fourth> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Fourth fourth = Fourth.builder()
                    .name(name + "_" + i)
                    .third(third)
                    .build();
            result.add(fourth);
        }
        return fourthRepository.saveAll(result);
    }

}
