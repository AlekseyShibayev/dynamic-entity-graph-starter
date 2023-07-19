package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.example.EntityGraphExtractor;
import com.company.app.infrastructure.SpringBootTestApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class EntityGraphExtractorManyTest extends SpringBootTestApplicationContext {

    @Autowired
    private EntityGraphExtractor extractor;
    @Autowired
    private FirstRepository firstRepository;
    @Autowired
    private ChatEntityGraphExtractorTestHelper helper;

    @Test
    void can_extract_many() {
        List<First> firsts = testEntityFactory.createEntityContext()
                .withFirstInfo("default")
                .createMany(10);

        List<First> extracted = extractor.createContext(firsts)
                .withFirstInfo()
                .extractAll();
        Assertions.assertEquals("default", extracted.get(0).getFirstInfo().getDescription());
        Assertions.assertEquals("default", extracted.get(5).getFirstInfo().getDescription());
        Assertions.assertEquals("default", extracted.get(9).getFirstInfo().getDescription());
    }

}