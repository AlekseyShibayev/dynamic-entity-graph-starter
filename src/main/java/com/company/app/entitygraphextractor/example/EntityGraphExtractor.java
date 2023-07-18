package com.company.app.entitygraphextractor.example;

import com.company.app.entitygraphextractor.domain.entity.First;
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

    public ChatContext createContext(First chat) {
        return ChatContext.of(chat, entityGraphExtractorHandler);
    }

}
