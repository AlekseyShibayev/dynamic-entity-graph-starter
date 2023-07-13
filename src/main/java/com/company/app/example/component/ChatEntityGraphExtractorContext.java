package com.company.app.example.component;

import com.company.app.domain.entity.Chat;
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

    public ChatEntityGraphExtractorContext withUserInfo() {
        parameters.add(ChatEntityGraphExtractorNode.builder()
                .nodeName("userInfo")
                .build());
        return this;
    }

//    public ChatEntityGraphExtractorContext withCustomFieldsValuesAndCustomField() {
//        parameters.add(ChatEntityGraphExtractorNode.builder()
//                .nodeName("customFieldsValues")
//                .child(ChatEntityGraphExtractorNode.builder()
//                        .nodeName("customField")
//                        .build())
//                .build());
//        return this;
//    }

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

    public Chat extract() {
        return handler.extract(this);
    }

}
