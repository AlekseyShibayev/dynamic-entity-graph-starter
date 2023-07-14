package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.component.EntityGraphExtractorHandler;
import com.company.app.entitygraphextractor.example.context.chat.ChatEntityGraphExtractorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityGraphExtractor {

    @Autowired
    private EntityGraphExtractorHandler mainHandler;

    public ChatEntityGraphExtractorContext createContext(Chat chat) {
        ChatEntityGraphExtractorContext extractorContext = ChatEntityGraphExtractorContext.of(chat);
        extractorContext.setMainHandler(mainHandler);
        return extractorContext;
    }

//    public <E> AbstractEntityGraphExtractorContext<E> createContext(ContextAdapter<E> context) {
//        AbstractEntityGraphExtractorContext<E> abstractEntityGraphExtractorContext = context.to();
//        abstractEntityGraphExtractorContext.setMainHandler(mainHandler);
//
//
//        return abstractEntityGraphExtractorContext;
//    }

}
