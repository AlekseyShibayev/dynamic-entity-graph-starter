package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import com.company.app.entitygraphextractor.example.EntityGraphExtractor;
import com.company.app.infrastructure.SpringBootTestApplicationContext;
import lombok.SneakyThrows;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EntityGraphExtractorTest extends SpringBootTestApplicationContext {

    @Autowired
    private EntityGraphExtractor extractor;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatEntityGraphExtractorTestHelper helper;

    @Test
    void exception_must_be_thrown() {
        Chat chat = testFactory.createChatContext()
                .withUserInfoDefault()
                .save();

        Assertions.assertThrows(LazyInitializationException.class, () -> chatRepository.findById(chat.getId()).get().getUserInfo().getName());
    }

    @Test
    void extractor_help_with_lazyInitException() {
        Chat chat = testFactory.createChatContext()
                .withUserInfoDefault()
                .save();

        Chat extracted = extractor.createContext(chat)
                .withUserInfo()
                .extract();
        Assertions.assertEquals("default", extracted.getUserInfo().getName());
        Assertions.assertThrows(LazyInitializationException.class, () -> chatRepository.findById(chat.getId()).get().getUserInfo().getName());
    }

    @Test
    void can_extract_collection_subscriptions() {
        Chat chat = testFactory.createChatContext()
                .withSubscriptionDefault()
                .save();

        Chat extracted = extractor.createContext(chat)
                .withSubscriptions()
                .extract();
        Assertions.assertEquals("default", extracted.getSubscriptions().stream().findFirst().get().getType());
        Assertions.assertThrows(LazyInitializationException.class, () -> extracted.getSubscriptions().stream().findFirst().get().getChats().get(0).getChatName());
    }

    @Test
    void can_extract_collection_histories() {
        Chat chat = testFactory.createChatContext()
                .withHistoryDefault()
                .save();

        Chat extracted = extractor.createContext(chat)
                .withHistories()
                .extract();
        Assertions.assertEquals("default", extracted.getHistories().get(0).getMessage());
    }

    @Test
    void can_extract_collection_depth_2() {
        Chat chat = testFactory.createChatContext()
                .withUserInfoDefault()
                .withSubscriptionAndSubscriptionInfoDefault()
                .save();

        Chat extracted = extractor.createContext(chat)
                .withSubscriptionsAndSubscriptionInfos()
                .withUserInfo()
                .extract();
        Assertions.assertEquals("default", extracted.getSubscriptions().stream().findFirst().get()
                .getSubscriptionInfos().get(0).getType());
    }

    @SneakyThrows
    @Test
    void can_extract_collection_and_another_collection_with_transaction() {
        Chat chat = testFactory.createChatContext()
                .withSubscriptionDefault()
                .withHistoryDefault()
                .save();
        Assertions.assertDoesNotThrow(() -> helper.test(extractor, chat));
    }

    // todo починить!
//    @Test
    void can_extract_collection_and_other_collection_with_out_transaction() {
        Chat chat = testFactory.createChatContext()
                .withSubscriptionDefault()
                .withHistoryDefault()
                .save();

        Chat extracted = extractor.createContext(chat)
                .withHistories()
                .withSubscriptions()
                .extract();
        Assertions.assertEquals("default", extracted.getHistories().get(0).getMessage());
    }

}