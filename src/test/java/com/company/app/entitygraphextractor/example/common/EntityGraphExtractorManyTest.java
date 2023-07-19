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
    private EntityGraphExtractor entityGraphExtractor;
    @Autowired
    private FirstRepository firstRepository;
    @Autowired
    private ChatEntityGraphExtractorTestHelper helper;

    @Test
    void can_extract_many_10() {
        List<First> firsts = testEntityFactory.createEntityContext()
                .withFirstInfo("default")
                .createMany(10);

        List<First> extracted = entityGraphExtractor.createContext(firsts)
                .withFirstInfo()
                .extractAll();
        Assertions.assertEquals("default", extracted.get(0).getFirstInfo().getDescription());
        Assertions.assertEquals("default", extracted.get(5).getFirstInfo().getDescription());
        Assertions.assertEquals("default", extracted.get(9).getFirstInfo().getDescription());
    }

    @Test
    void can_extract_many_1000() {
        List<First> firsts = testEntityFactory.createEntityContext()
                .withSecond("name", 10)
                .withFirstInfo("default")
                .createMany(1000);

        List<First> extracted = entityGraphExtractor.createContext(firsts)
                .withSeconds()
                .withFirstInfo()
                .extractAll();
        Assertions.assertEquals("default", extracted.get(0).getFirstInfo().getDescription());
        Assertions.assertEquals("default", extracted.get(555).getFirstInfo().getDescription());
        Assertions.assertEquals("default", extracted.get(999).getFirstInfo().getDescription());
        Assertions.assertEquals("name_0", extracted.get(0).getSeconds().get(0).getName());
        Assertions.assertEquals("name_9", extracted.get(999).getSeconds().get(9).getName());
    }

}