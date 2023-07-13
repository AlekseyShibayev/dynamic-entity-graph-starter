package com.company.app.infrastructure.testfactory;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.entity.Subscription;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import com.company.app.entitygraphextractor.domain.repository.HistoryRepository;
import com.company.app.entitygraphextractor.domain.repository.SubscriptionRepository;
import com.company.app.entitygraphextractor.domain.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;

@Component
@RequiredArgsConstructor
public class TestFactory {

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    private final ChatRepository chatRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final HistoryRepository historyRepository;
    private final UserInfoRepository userInfoRepository;

    public TestFactoryChatContext createContext() {
        Chat chat = Chat.builder()
                .chatName(String.valueOf(atomicInteger.addAndGet(1)))
                .build();
        chatRepository.save(chat);

        return TestFactoryChatContext.builder()
                .chat(chat)

                .testFactory(this)
                .chatRepository(chatRepository)
                .userInfoRepository(userInfoRepository)
                .historyRepository(historyRepository)
                .subscriptionRepository(subscriptionRepository)
                .build();
    }

    public Subscription createSubscription(Chat chat) {
        Subscription build = Subscription.builder().type("default").chats(Collections.singletonList(chat)).build();
        return subscriptionRepository.save(build);
    }

}
