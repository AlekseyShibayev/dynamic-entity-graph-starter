package com.company.app.entitygraphextractor.example.chat;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatEntityGraphExtractor {

    @Autowired
    private EntityGraphExtractorHandler mainHandler;

    public ChatContext createContext(Chat chat) {
        return ChatContext.of(chat, mainHandler);
    }

}
