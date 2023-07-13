package com.company.app.infrastructure.testfactory;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.entity.History;
import com.company.app.entitygraphextractor.domain.entity.Subscription;
import com.company.app.entitygraphextractor.domain.entity.UserInfo;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import com.company.app.entitygraphextractor.domain.repository.HistoryRepository;
import com.company.app.entitygraphextractor.domain.repository.SubscriptionRepository;
import com.company.app.entitygraphextractor.domain.repository.UserInfoRepository;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;

@Data
@Builder
public class TestChatFactoryContext {

    private Chat chat;

    private TestChatFactory testFactory;

    private ChatRepository chatRepository;
    private SubscriptionRepository subscriptionRepository;
    private HistoryRepository historyRepository;
    private UserInfoRepository userInfoRepository;

    /**
     * terminal operation
     */
    public Chat save() {
        return chatRepository.save(chat);
    }

    public TestChatFactoryContext withUserInfoDefault() {
        chat.setUserInfo(UserInfo.builder().name("default").role("default").gender("default").chat(chat).build());
        return this;
    }

    public TestChatFactoryContext withSubscriptionDefault() {
        Subscription subscription = testFactory.createSubscription(chat);
        chat.setSubscriptions(Collections.singleton(subscription));
        return this;
    }

    public TestChatFactoryContext withSubscriptionAndSubscriptionInfoDefault() {
        Subscription subscription = testFactory.createSubscription(chat);
        testFactory.createSubscriptionInfo(subscription);
        chat.setSubscriptions(Collections.singleton(subscription));
        return this;
    }

    public TestChatFactoryContext withHistoryDefault() {
        History history = testFactory.createHistory(chat);
        chat.setHistories(Collections.singletonList(history));
        return this;
    }

}
