package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.context.ChatContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityGraphExtractor {

    @Autowired
    private EntityGraphExtractorHandler mainHandler;

    public ChatContext createContext(Chat chat) {
        return ChatContext.of(chat, mainHandler);
    }

}
