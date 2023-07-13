package com.company.app.entitygraphextractor.example;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.entity.Subscription;
import com.company.app.entitygraphextractor.domain.entity.UserInfo;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import com.company.app.entitygraphextractor.domain.repository.SubscriptionRepository;
import com.company.app.springboot.SpringBootTestApplicationContext;
import com.google.common.collect.Lists;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

class ChatEntityGraphExtractorTest extends SpringBootTestApplicationContext {

    @Autowired
    private ChatEntityGraphExtractor extractor;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    void exception_must_be_thrown() {
        Chat chat = Chat.builder()
                .chatName("1")
                .userInfo(UserInfo.builder()
                        .name("1")
                        .build())
                .build();
        chatRepository.save(chat);

        Assertions.assertThrows(LazyInitializationException.class, () -> chatRepository.findById(chat.getId()).get().getUserInfo().getName());
    }

    @Test
    void extractor_help_with_lazyInitException() {
        Chat chat = Chat.builder()
                .chatName("1")
                .userInfo(UserInfo.builder()
                        .name("1")
                        .build())
                .build();
        chatRepository.save(chat);

        Chat extracted = extractor.createContext(chat.getId())
                .withUserInfo()
                .extract();
        Assertions.assertEquals("1", extracted.getUserInfo().getName());
    }

    @Test
    void extractor_help_with_N_plus_one() {
        Chat chat = Chat.builder()
                .chatName("name")
                .build();
        chatRepository.save(chat);

        Subscription subscription = Subscription.builder()
                .type("type")
                .chats(Collections.singletonList(chat))
                .build();
        subscriptionRepository.save(subscription);

        chat.setSubscriptions(List.of(subscription));
        chatRepository.save(chat);

        Assertions.assertThrows(LazyInitializationException.class, () -> chatRepository.findById(chat.getId()).get().getSubscriptions().get(0).getId());

        Chat extracted = extractor.createContext(chat.getId())
                .withSubscriptions()
                .extract();
        Assertions.assertEquals("type", extracted.getSubscriptions().get(0).getType());
    }

}