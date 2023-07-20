package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.infrastructure.SpringBootTestApplicationContext;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EntityGraphExtractorTest extends SpringBootTestApplicationContext {

    @Autowired
    private FirstRepository firstRepository;
    @Autowired
    private ChatEntityGraphExtractorTestHelper helper;

    @Test
    void exception_must_be_thrown() {
        First first = testEntityFactory.createFirstEntityContext()
                .withFirstInfo("default")
                .createOne();

        Assertions.assertThrows(LazyInitializationException.class, () -> firstRepository.findById(first.getId()).get().getFirstInfo().getDescription());
    }

    @Test
    void can_extract_objects_depth_1() {
        First first = testEntityFactory.createFirstEntityContext()
                .withFirstInfo("firstInfo")
                .createOne();

        First extracted = entityGraphExtractor.createContext(first)
                .withFirstInfo()
                .extractOne();

        Assertions.assertEquals("firstInfo", extracted.getFirstInfo().getDescription());
    }

    @Test
    void can_extract_collections_depth_1() {
        First first = testEntityFactory.createFirstEntityContext()
                .withSecond("second")
                .createOne();

        First extracted = entityGraphExtractor.createContext(first)
                .withSeconds()
                .extractOne();
        Assertions.assertEquals("second_0", extracted.getSeconds().stream().findFirst().get().getName());
    }

    @Test
    void can_extract_collection_depth_1_and_object_depth_1() {
        First first = testEntityFactory.createFirstEntityContext()
                .withFirstInfo("firstInfo")
                .withSecond("second", 100)
                .createOne();

        First extracted = entityGraphExtractor.createContext(first)
                .withFirstInfo()
                .withSeconds()
                .extractOne();
        Assertions.assertEquals("second_0", extracted.getSeconds().stream().findFirst().get()
                .getName());
    }

    @Test
    void can_extract_collection_depth_1_and_object_depth_2() {
        First first = testEntityFactory.createFirstEntityContext()
                .withFirstInfo("firstInfo")
                .withSecond("second", 100)
                .withSecondInfo("secondInfo")
                .createOne();

        First extracted = entityGraphExtractor.createContext(first)
                .withFirstInfo()
                .withSecondsAndSecondInfo()
                .extractOne();
        Assertions.assertEquals("secondInfo", extracted.getSeconds().stream().findFirst().get().getSecondInfo().getDescription());
    }

    @Test
    void can_extract_collection_depth_2() {
        First first = testEntityFactory.createFirstEntityContext()
                .withFirstInfo("firstInfo")
                .withSecond("second", 2)
                .withSecondInfo("secondInfo")
                .withThird("third", 2)
                .createOne();

        First extracted = entityGraphExtractor.createContext(first)
                .withFirstInfo()
                .withSecondsAndThirds()
                .extractOne();
        Assertions.assertEquals("third_0", extracted.getSeconds().stream().findFirst().get().getThirds().get(0).getName());
    }

    @Test
    void can_extract_collection_depth_2_and_object_depth_3() {
        First first = testEntityFactory.createFirstEntityContext()
                .withFirstInfo("firstInfo")
                .withSecond("second", 2)
                .withSecondInfo("secondInfo")
                .withThird("third", 2)
                .withThirdInfo("thirdInfo")
                .createOne();

        First extracted = entityGraphExtractor.createContext(first)
                .withSecondsAndThirdsAndThirdInfo()
                .extractOne();
        Assertions.assertEquals("thirdInfo", extracted.getSeconds().get(0).getThirds().get(0).getThirdInfo().getDescription());
    }

    @Test
    void can_extract_all_depth_3() {
        First first = testEntityFactory.createFirstEntityContext()
                .withFirstInfo("firstInfo")
                .withSecond("second", 2)
                .withSecondInfo("secondInfo")
                .withThird("third", 2)
                .withThirdInfo("thirdInfo")
                .createOne();

        First extracted = entityGraphExtractor.createContext(first)
                .withFirstInfo()
//                .withSeconds()
                .withSecondsAndSecondInfo()
                .withSecondsAndThirds()
                .withSecondsAndThirdsAndThirdInfo()
                .extractOne();

        Assertions.assertEquals("firstInfo", extracted.getFirstInfo().getDescription());
        Assertions.assertEquals("second_0", extracted.getSeconds().get(0).getName());
        Assertions.assertEquals("secondInfo", extracted.getSeconds().get(0).getSecondInfo().getDescription());
        Assertions.assertEquals("third_0", extracted.getSeconds().get(0).getThirds().get(0).getName());
        Assertions.assertEquals("thirdInfo", extracted.getSeconds().get(0).getThirds().get(0).getThirdInfo().getDescription());
    }

}