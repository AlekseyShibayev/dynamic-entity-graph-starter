package com.company.app.entitygraphextractor.example.context;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ChatContextImpl extends ChatContext<Chat> {

    @Override
    public Class<Chat> getClass_() {
        return Chat.class;
    }

    @Override
    public Long getId_() {
        return entity.getId();
    }

    public ChatContextImpl withUserInfo() {
        nodes.add(Node.builder()
                .name("userInfo")
                .build());
        return this;
    }

    public ChatContextImpl withHistories() {
        nodes.add(Node.builder()
                .name("histories")
                .build());
        return this;
    }

    public ChatContextImpl withSubscriptions() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .build());
        return this;
    }

    public ChatContextImpl withSubscriptionsAndChats() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("chats")
                        .build())
                .build());
        return this;
    }

    public ChatContextImpl withSubscriptionsAndSubscriptionInfos() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("subscriptionInfos")
                        .build())
                .build());
        return this;
    }


}
