package com.company.app.entitygraphextractor.example.context.chat;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.data.AbstractEntityGraphExtractorContext;
import com.company.app.entitygraphextractor.example.common.data.Node;

public class ChatEntityGraphExtractorContext extends AbstractEntityGraphExtractorContext<Chat> {

    private final Chat chat;

    private ChatEntityGraphExtractorContext(Chat chat) {
        this.chat = chat;
    }

    public static ChatEntityGraphExtractorContext of(Chat chat) {
        return new ChatEntityGraphExtractorContext(chat);
    }

    @Override
    public Class<Chat> getClass_() {
        return Chat.class;
    }

    @Override
    public Long getId_() {
        return chat.getId();
    }

    public ChatEntityGraphExtractorContext withUserInfo() {
        nodes.add(Node.builder()
                .name("userInfo")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withHistories() {
        nodes.add(Node.builder()
                .name("histories")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptions() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptionsAndChats() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("chats")
                        .build())
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptionsAndSubscriptionInfos() {
        nodes.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("subscriptionInfos")
                        .build())
                .build());
        return this;
    }

}
