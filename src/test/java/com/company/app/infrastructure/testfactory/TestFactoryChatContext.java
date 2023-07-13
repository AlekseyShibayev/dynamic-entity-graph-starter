package com.company.app.infrastructure.testfactory;

import com.company.app.entitygraphextractor.domain.entity.Chat;
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
public class TestFactoryChatContext {

    private Chat chat;

    private TestFactory testFactory;

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
    public TestFactoryChatContext withUserInfoDefault() {
        chat.setUserInfo(UserInfo.builder().name("default").role("default").gender("default").chat(chat).build());
        return this;
    }

    public TestFactoryChatContext withSubscriptionDefault() {
        Subscription subscription = testFactory.createSubscription(chat);
        chat.setSubscriptions(Collections.singletonList(subscription));
        return this;
    }

}
