package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.component.EntityGraphExtractorHandler;
import com.company.app.entitygraphextractor.example.context.ChatEntityGraphExtractorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityGraphExtractor {

    @Autowired
    private EntityGraphExtractorHandler mainHandler;

    public ChatEntityGraphExtractorContext createContext(Chat chat) {
        ChatEntityGraphExtractorContext extractorContext = ChatEntityGraphExtractorContext.of(chat);
        extractorContext.setMainHandler(mainHandler);
        return extractorContext;
    }

//    public <E> EntityGraphExtractorContext<E> createContext_(Chat chat) {
//        context.of()
//        contextOf.of()
//        return ChatEntityGraphExtractorContext.of(chat, mainHandler);
//    }

}
