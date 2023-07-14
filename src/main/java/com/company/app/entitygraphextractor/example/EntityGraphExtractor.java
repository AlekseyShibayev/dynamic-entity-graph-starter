package com.company.app.entitygraphextractor.example;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.Context;
import com.company.app.entitygraphextractor.example.common.PrototypeContextBeanFactory;
import com.company.app.entitygraphextractor.example.context.ChatContext;
import com.company.app.entitygraphextractor.example.context.ChatContextImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntityGraphExtractor {

    private final ChatContext chatContext;
    private final PrototypeContextBeanFactory prototypeContextBeanFactory;

    public ChatContext createContext(Chat chat) {
        chatContext.setEntity_(chat);
        return chatContext;
    }

    public <E> Context<E> createContext_(E e) {
        Context<E> eContext = prototypeContextBeanFactory.get(e);
        eContext.setEntity_(e);
        return eContext;
    }

}
