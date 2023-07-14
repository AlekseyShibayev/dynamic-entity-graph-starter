package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.example.EntityGraphExtractor;
import com.company.app.infrastructure.SpringBootTestApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class PrototypeContextBeanFactoryTest extends SpringBootTestApplicationContext {

    @Autowired
    private EntityGraphExtractor extractor;

    @Test
    void test_1() {
        Chat chat = testFactory.createChatContext()
                .withUserInfoDefault()
                .save();

        Context<Chat> context = extractor.createContext_(chat);
        Chat extract = context.extract();
        log.debug("123");
    }

}
