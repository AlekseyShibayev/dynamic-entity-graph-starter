package com.company.app.example;

import com.company.app.example.component.ChatEntityGraphExtractorContext;
import com.company.app.example.component.ChatEntityGraphExtractorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatEntityGraphExtractor {

    @Autowired
    private ChatEntityGraphExtractorHandler handler;

    public ChatEntityGraphExtractorContext createContext(Long id) {
        log.debug("[{}]: start", id);
        return ChatEntityGraphExtractorContext.of(id, handler);
    }

}
