package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import com.company.app.entitygraphextractor.example.EntityGraphExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ChatEntityGraphExtractorTestHelper {

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    public Chat test(EntityGraphExtractor extractor, Chat chat) {
        chatRepository.findById(chat.getId());

        Chat extracted = extractor.createContext(chat)
                .withHistories()
                .withSubscriptions()
                .extract();

        return extracted;
    }

}
