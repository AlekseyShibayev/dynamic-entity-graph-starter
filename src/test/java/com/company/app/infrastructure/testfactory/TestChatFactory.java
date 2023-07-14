package com.company.app.infrastructure.testfactory;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.entity.History;
import com.company.app.entitygraphextractor.domain.entity.Subscription;
import com.company.app.entitygraphextractor.domain.entity.SubscriptionInfo;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import com.company.app.entitygraphextractor.domain.repository.HistoryRepository;
import com.company.app.entitygraphextractor.domain.repository.SubscriptionInfoRepository;
import com.company.app.entitygraphextractor.domain.repository.SubscriptionRepository;
import com.company.app.entitygraphextractor.domain.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Transactional
public class TestChatFactory {

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    private final ChatRepository chatRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final HistoryRepository historyRepository;
    private final UserInfoRepository userInfoRepository;
    private final SubscriptionInfoRepository subscriptionInfoRepository;

    public TestChatFactoryContext createChatContext(Chat chat) {

        return TestChatFactoryContext.builder()
                .chat(chat)

                .testFactory(this)

                .chatRepository(chatRepository)
                .userInfoRepository(userInfoRepository)
                .historyRepository(historyRepository)
                .subscriptionRepository(subscriptionRepository)
                .build();
    }

    public TestChatFactoryContext createChatContext() {
        Chat chat = createChatDefault();
        return createChatContext(chat);
    }

    public Chat createChatDefault() {
        Chat chat = Chat.builder()
                .chatName(String.valueOf(atomicInteger.addAndGet(1)))
                .build();
        return chatRepository.save(chat);
    }

    public Subscription createSubscription(Chat chat) {
        Subscription build = Subscription.builder().type("default").chats(Collections.singletonList(chat)).build();
        return subscriptionRepository.save(build);
    }

    public SubscriptionInfo createSubscriptionInfo(Subscription subscription) {
        SubscriptionInfo info = SubscriptionInfo.builder().type("default").subscription(subscription).build();
        return subscriptionInfoRepository.save(info);
    }

    public History createHistory(Chat chat) {
        History history = History.builder().chat(chat).date(new Date()).message("default").build();
        return historyRepository.save(history);
    }

}
