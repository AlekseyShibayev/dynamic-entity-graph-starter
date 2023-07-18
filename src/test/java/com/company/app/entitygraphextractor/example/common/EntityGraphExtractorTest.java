package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.domain.entity.FirstInfo;
import com.company.app.entitygraphextractor.domain.repository.FirstInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.FirstRepository;
import com.company.app.entitygraphextractor.example.EntityGraphExtractor;
import com.company.app.infrastructure.SpringBootTestApplicationContext;
import com.company.app.infrastructure.testentityfactory.component.CreateAction;
import com.company.app.infrastructure.testentityfactory.component.Context;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    void extractor_help_with_lazyInitException() {
        First chat = testEntityFactory.createEntityContext()
                .withFirstInfo("default")
                .createOne();

        First extracted = extractor.createContext(chat)
                .withFirstInfo()
                .extract();

        Assertions.assertEquals("default", extracted.getFirstInfo().getDescription());
    }

//    @Test
//    void can_extract_collection_subscriptions() {
//        First chat = testEntityFactory.createEntityContext()
//                .withSubscriptionDefault()
//                .save();
//
//        First extracted = extractor.createContext(chat)
//                .withSubscriptions()
//                .extract();
//        Assertions.assertEquals("default", extracted.getSubscriptions().stream().findFirst().get().getType());
//        Assertions.assertThrows(LazyInitializationException.class, () -> extracted.getSubscriptions().stream().findFirst().get().getChats().get(0).getChatName());
//    }
//
//    @Test
//    void can_extract_collection_histories() {
//        First chat = testEntityFactory.createEntityContext()
//                .withHistoryDefault()
//                .save();
//
//        First extracted = extractor.createContext(chat)
//                .withHistories()
//                .extract();
//        Assertions.assertEquals("default", extracted.getHistories().get(0).getMessage());
//    }
//
//    @Test
//    void can_extract_collection_depth_2() {
//        First chat = testEntityFactory.createEntityContext()
//                .withUserInfoDefault()
//                .withSubscriptionAndSubscriptionInfoDefault()
//                .save();
//
//        First extracted = extractor.createContext(chat)
//                .withSubscriptionsAndSubscriptionInfos()
//                .withUserInfo()
//                .extract();
//        Assertions.assertEquals("default", extracted.getSubscriptions().stream().findFirst().get()
//                .getSubscriptionInfos().get(0).getType());
//    }
//
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