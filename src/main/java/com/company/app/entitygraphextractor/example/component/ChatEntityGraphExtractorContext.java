package com.company.app.entitygraphextractor.example.component;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.data.Node;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatEntityGraphExtractorContext {

    private final Long id;
    private final ChatEntityGraphExtractorHandler handler;

    private final List<Node> parameters = new ArrayList<>();

    private ChatEntityGraphExtractorContext(Long id, ChatEntityGraphExtractorHandler handler) {
        this.id = id;
        this.handler = handler;
    }

    public static ChatEntityGraphExtractorContext of(Long id, ChatEntityGraphExtractorHandler handler) {
        return new ChatEntityGraphExtractorContext(id, handler);
    }

    public Chat extract() {
        return handler.extract(this);
    }

    public ChatEntityGraphExtractorContext withUserInfo() {
        parameters.add(Node.builder()
                .name("userInfo")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withHistories() {
        parameters.add(Node.builder()
                .name("histories")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptions() {
        parameters.add(Node.builder()
                .name("subscriptions")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptionsAndChats() {
        parameters.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("chats")
                        .build())
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptionsAndSubscriptionInfos() {
        parameters.add(Node.builder()
                .name("subscriptions")
                .child(Node.builder()
                        .name("subscriptionInfos")
                        .build())
                .build());
        return this;
    }

}
