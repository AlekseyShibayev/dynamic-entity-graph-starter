package com.company.app.entitygraphextractor.example.context;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.AbstractContext;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorHandler;
import com.company.app.entitygraphextractor.example.common.Node;

public class ChatContext extends AbstractContext<Chat> {

    private ChatContext(Chat chat, EntityGraphExtractorHandler handler) {
        this.entity = chat;
        this.handler = handler;
    }

    public static ChatContext of(Chat chat, EntityGraphExtractorHandler handler) {
        return new ChatContext(chat, handler);
    }

    @Override
    public Class<Chat> getClass_() {
        return Chat.class;
    }

    @Override
    public Long getId_() {
        return entity.getId();
    }

    public ChatContext withUserInfo() {
        nodes.add(Node.builder()
                .name("userInfo")
                .build());
        return this;
    }

    public ChatContext withHistories() {
        nodes.add(Node.builder()
                .name("histories")
                .build());
        return this;
    }

    public ChatContext withSubscriptions() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .build());
        return this;
    }

    public ChatContext withSubscriptionsAndChats() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("chats")
                        .build())
                .build());
        return this;
    }

    public ChatContext withSubscriptionsAndSubscriptionInfos() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("subscriptionInfos")
                        .build())
                .build());
        return this;
    }

}
