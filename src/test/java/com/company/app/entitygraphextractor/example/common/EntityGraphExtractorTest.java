package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.example.EntityGraphExtractor;
import com.company.app.infrastructure.SpringBootTestApplicationContext;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class EntityGraphExtractorTest extends SpringBootTestApplicationContext {

    @Autowired
    private EntityGraphExtractor extractor;
    @Autowired
    private FirstRepository firstRepository;
    @Autowired
    private ChatEntityGraphExtractorTestHelper helper;

    @Test
    void exception_must_be_thrown() {
        First first = testEntityFactory.createEntityContext()
                .withFirstInfo("default")
                .createOne();

        Assertions.assertThrows(LazyInitializationException.class, () -> firstRepository.findById(first.getId()).get().getFirstInfo().getDescription());
    }

    @Test
    void can_extract_objects_depth_1() {
        First first = testEntityFactory.createEntityContext()
                .withFirstInfo("default")
                .createOne();

        First extracted = extractor.createContext(first)
                .withFirstInfo()
                .extractOne();

        Assertions.assertEquals("default", extracted.getFirstInfo().getDescription());
    }

    @Test
    void can_extract_collections_depth_1() {
        First first = testEntityFactory.createEntityContext()
                .withSecond("second")
                .createOne();

        First extracted = extractor.createContext(first)
                .withSeconds()
                .extractOne();
        Assertions.assertEquals("second_0", extracted.getSeconds().stream().findFirst().get().getName());
    }

    @Test
    void can_extract_collection_depth_1_and_object_depth_1() {
        First first = testEntityFactory.createEntityContext()
                .withFirstInfo("default")
                .withSecond("second")
                .createOne();

        First extracted = extractor.createContext(first)
                .withFirstInfo()
                .withSeconds()
                .extractOne();
        Assertions.assertEquals("second_0", extracted.getSeconds().stream().findFirst().get()
                .getName());
    }

    @Test
    void can_extract_collection_depth_1_and_object_depth_1_many() {
        First first = testEntityFactory.createEntityContext()
                .withFirstInfo("default")
                .withSecond("second", 100)
                .createOne();

        First extracted = extractor.createContext(first)
                .withFirstInfo()
                .withSeconds()
                .extractOne();
        Assertions.assertEquals("second_0", extracted.getSeconds().stream().findFirst().get()
                .getName());
    }

//    @SneakyThrows
//    @Test
//    void can_extract_collection_and_another_collection_with_transaction() {
//        First chat = testEntityFactory.createEntityContext()
//                .withSubscriptionDefault()
//                .withHistoryDefault()
//                .save();
//        Assertions.assertDoesNotThrow(() -> helper.test(extractor, chat));
//    }
//
//    // todo починить!
////    @Test
//    void can_extract_collection_and_other_collection_with_out_transaction() {
//        First chat = testEntityFactory.createEntityContext()
//                .withSubscriptionDefault()
//                .withHistoryDefault()
//                .save();
//
//        First extracted = extractor.createContext(chat)
//                .withHistories()
//                .withSubscriptions()
//                .extract();
//        Assertions.assertEquals("default", extracted.getHistories().get(0).getMessage());
//    }

}