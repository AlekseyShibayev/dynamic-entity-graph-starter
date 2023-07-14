package com.company.app.entitygraphextractor.example.chat;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ChatEntityGraphExtractorTestHelper {

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    public Chat test(ChatEntityGraphExtractor extractor, Chat chat) {
        chatRepository.findById(chat.getId());

        Chat extracted = extractor.createContext(chat)
                .withHistories()
                .withSubscriptions()
                .extract();

        return extracted;
    }

}
