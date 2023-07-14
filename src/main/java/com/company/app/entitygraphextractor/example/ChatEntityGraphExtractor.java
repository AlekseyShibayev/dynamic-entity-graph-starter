package com.company.app.entitygraphextractor.example;

import com.company.app.entitygraphextractor.example.data.ChatContext;
import com.company.app.entitygraphextractor.example.component.ChatEntityGraphExtractorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatEntityGraphExtractor {

    @Autowired
    private ChatEntityGraphExtractorHandler handler;

    public ChatContext createContext(Long id) {
        log.debug("[{}]: start", id);
        return ChatContext.of(id, handler);
    }

}
