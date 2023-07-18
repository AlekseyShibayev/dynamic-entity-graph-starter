package com.company.app.infrastructure.testentityfactory.component;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.domain.repository.FourthRepository;
import com.company.app.entitygraphextractor.domain.repository.SecondRepository;
import com.company.app.entitygraphextractor.domain.repository.ThirdRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class TestEntityFactoryFinisher {

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    private final FirstRepository firstRepository;
    private final SecondRepository secondRepository;
    private final ThirdRepository thirdRepository;
    private final FourthRepository fourthRepository;

    @Transactional
    public List<First> create(FirstContext firstContext) {
        List<First> result = new ArrayList<>();
        for (int i = 0; i < firstContext.getAmount(); i++) {
            result.add(createOne(firstContext));
        }
        return result;
    }

    private First createOne(FirstContext firstContext) {
        First first = First.builder()
                .name(String.valueOf(atomicInteger.getAndAdd(1)))
                .seconds(Lists.newArrayList())
                .build();
        firstRepository.save(first);
        firstContext.setFirst(first);

        firstContext.getActionsList().forEach(firstActions -> firstActions.doEnrich(firstContext));

        First prepared = firstContext.getFirst();
        return firstRepository.save(prepared);
    }

}
