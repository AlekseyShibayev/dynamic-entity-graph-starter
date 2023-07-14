package com.company.app.entitygraphextractor.example;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorHandler;
import com.company.app.entitygraphextractor.example.context.ChatContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityGraphExtractor {

    @Autowired
    private EntityGraphExtractorHandler entityGraphExtractorHandler;

    public ChatContext createContext(Chat chat) {
        ChatContext extractorContext = ChatContext.of(chat);
        extractorContext.setMainHandler(entityGraphExtractorHandler);
        return extractorContext;
    }

}
