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
    private EntityGraphExtractorHandler entityGraphExtractorHandler;

    public ChatEntityGraphExtractorContext createContext(Chat chat) {
        ChatEntityGraphExtractorContext extractorContext = ChatEntityGraphExtractorContext.of(chat);
        extractorContext.setMainHandler(entityGraphExtractorHandler);
        return extractorContext;
    }

}
