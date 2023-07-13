package com.company.app.entitygraphextractor.example;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import com.company.app.infrastructure.SpringBootTestApplicationContext;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ChatEntityGraphExtractorTest extends SpringBootTestApplicationContext {

    @Autowired
    private ChatEntityGraphExtractor extractor;
    @Autowired
    private ChatRepository chatRepository;

    @Test
    void exception_must_be_thrown() {
        Chat chat = testFactory.createContext()
                .withUserInfoDefault()
                .save();

        Assertions.assertThrows(LazyInitializationException.class, () -> chatRepository.findById(chat.getId()).get().getUserInfo().getName());
    }

    @Test
    void extractor_help_with_lazyInitException() {
        Chat chat = testFactory.createContext()
                .withUserInfoDefault()
                .save();

        Chat extracted = extractor.createContext(chat.getId())
                .withUserInfo()
                .extract();
        Assertions.assertEquals("default", extracted.getUserInfo().getName());
        Assertions.assertThrows(LazyInitializationException.class, () -> chatRepository.findById(chat.getId()).get().getUserInfo().getName());
    }

    @Test
    void can_extract_collection() {
        Chat chat = testFactory.createContext()
                .withSubscriptionDefault()
                .save();

        Chat extracted = extractor.createContext(chat.getId())
                .withSubscriptions()
                .extract();
        Assertions.assertEquals("default", extracted.getSubscriptions().get(0).getType());
        Assertions.assertThrows(LazyInitializationException.class, () -> extracted.getSubscriptions().get(0).getChats().get(0).getChatName());
    }

}