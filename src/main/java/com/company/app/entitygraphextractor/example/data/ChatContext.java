package com.company.app.entitygraphextractor.example.data;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.component.ChatEntityGraphExtractorHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatContext {

    private final Long id;
    private final ChatEntityGraphExtractorHandler handler;

    private final List<Node> parameters = new ArrayList<>();

    private ChatContext(Long id, ChatEntityGraphExtractorHandler handler) {
        this.id = id;
        this.handler = handler;
    }

    public static ChatContext of(Long id, ChatEntityGraphExtractorHandler handler) {
        return new ChatContext(id, handler);
    }

    public Chat extract() {
        return handler.extract(this);
    }

    public ChatContext withUserInfo() {
        parameters.add(Node.builder()
                .name("userInfo")
                .build());
        return this;
    }

    public ChatContext withHistories() {
        parameters.add(Node.builder()
                .name("histories")
                .build());
        return this;
    }

    public ChatContext withSubscriptions() {
        parameters.add(Node.builder()
                .name("subscriptions")
                .build());
        return this;
    }

    public ChatContext withSubscriptionsAndChats() {
        parameters.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("chats")
                        .build())
                .build());
        return this;
    }

    public ChatContext withSubscriptionsAndSubscriptionInfos() {
        parameters.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("subscriptionInfos")
                        .build())
                .build());
        return this;
    }

}
