package com.company.app.entitygraphextractor.example.context;

import com.company.app.entitygraphextractor.domain.entity.First;
import com.company.app.entitygraphextractor.example.common.AbstractContext;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorHandler;

public class ChatContext extends AbstractContext<First> {

    private ChatContext(First chat, EntityGraphExtractorHandler handler) {
        this.entity = chat;
        this.handler = handler;
    }

    public static ChatContext of(First chat, EntityGraphExtractorHandler handler) {
        return new ChatContext(chat, handler);
    }

    @Override
    public Class<First> getClass_() {
        return First.class;
    }

    @Override
    public Long getId_() {
        return entity.getId();
    }

    public ChatContext withUserInfo() {
        addParameter("userInfo");
        return this;
    }

    public ChatContext withHistories() {
        addParameter("histories");
        return this;
    }

    public ChatContext withSubscriptions() {
        addParameter("subscriptions");
        return this;
    }

    public ChatContext withSubscriptionsAndChats() {
        addParameter("subscriptions", "chats");
        return this;
    }

    public ChatContext withSubscriptionsAndSubscriptionInfos() {
        addParameter("subscriptions", "subscriptionInfos");
        return this;
    }

}
