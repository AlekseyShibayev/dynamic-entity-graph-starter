package com.company.app.entitygraphextractor.example.temp;

import com.company.app.entitygraphextractor.example.common.EntityGraphExtractorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityGraphExtractor {

//    private static final Map<Class, Context_> MAP = Map.of(
//            Chat.class, new ChatContext()
//    );

    @Autowired
    private EntityGraphExtractorHandler mainHandler;

//    public <E> Context_<E> createContext(E e) {
//        Context_ context = MAP.get(e.getClass());
//
//        return Context.of(e);
//    }

//    public ChatContext createContext(Chat chat) {
//        return ChatContext.of(chat, mainHandler);
//    }

}
