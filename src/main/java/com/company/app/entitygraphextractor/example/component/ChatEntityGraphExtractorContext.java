package com.company.app.entitygraphextractor.example.component;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatEntityGraphExtractorContext {

    private final Long id;
    private final ChatEntityGraphExtractorHandler handler;

    private final List<ChatEntityGraphExtractorNode> parameters = new ArrayList<>();

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
        parameters.add(ChatEntityGraphExtractorNode.builder()
                .nodeName("userInfo")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withHistories() {
        parameters.add(ChatEntityGraphExtractorNode.builder()
                .nodeName("histories")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptions() {
        parameters.add(ChatEntityGraphExtractorNode.builder()
                .nodeName("subscriptions")
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptionsAndChats() {
        parameters.add(ChatEntityGraphExtractorNode.builder()
                .nodeName("subscriptions")
                .child(ChatEntityGraphExtractorNode.builder()
                        .nodeName("chats")
                        .build())
                .build());
        return this;
    }

    public ChatEntityGraphExtractorContext withSubscriptionsAndSubscriptionInfos() {
        parameters.add(ChatEntityGraphExtractorNode.builder()
                .nodeName("subscriptions")
                .child(ChatEntityGraphExtractorNode.builder()
                        .nodeName("subscriptionInfos")
                        .build())
                .build());
        return this;
    }

}
