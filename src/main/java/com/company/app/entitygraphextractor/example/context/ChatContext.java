package com.company.app.entitygraphextractor.example.context;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.Context;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorHandler;
import com.company.app.entitygraphextractor.example.common.Node;

import java.util.ArrayList;
import java.util.List;

public class ChatContext implements Context<Chat> {

    private final Chat chat;
    private final EntityGraphExtractorHandler mainHandler;
    private final List<Node> nodes = new ArrayList<>();

    private ChatContext(Chat chat, EntityGraphExtractorHandler mainHandler) {
        this.chat = chat;
        this.mainHandler = mainHandler;
    }

    public static ChatContext of(Chat chat, EntityGraphExtractorHandler mainHandler) {
        return new ChatContext(chat, mainHandler);
    }

    @Override
    public Class<Chat> getClass_() {
        return Chat.class;
    }

    @Override
    public Long getId_() {
        return chat.getId();
    }

    @Override
    public List<Node> getNodes_() {
        return nodes;
    }

    @Override
    public Chat extract() {
        return mainHandler.extract(this);
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
