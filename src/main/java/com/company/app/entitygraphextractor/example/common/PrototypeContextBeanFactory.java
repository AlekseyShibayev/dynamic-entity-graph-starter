package com.company.app.entitygraphextractor.example.common;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import com.company.app.entitygraphextractor.domain.entity.Subscription;
import com.company.app.entitygraphextractor.example.context.ChatContext;
import com.company.app.entitygraphextractor.example.temp.Temp;
import com.company.app.entitygraphextractor.example.context.SubscriptionContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
//@Configuration
@RequiredArgsConstructor
public class PrototypeContextBeanFactory {

    private final List<Context> contexts;
//    private final ChatContext chatContext;
//    private final SubscriptionContext subscriptionContext;



//    private final Function<String, Context> beanFactory;
//
//    public <E> Context<E> getPrototypeInstance(String name) {
//        return beanFactory.apply(name);
//    }



    public <E> Context<E> get(E e) {
        for (Context context : contexts) {
            if (context.getClass_().equals(e.getClass())) {
                return context;
            }
        }
        throw new UnsupportedOperationException("123");
    }


//        if (e.getClass().equals(Chat.class)) {
//            return ("chatContext");
//        } else if (e.getClass().equals(Subscription.class)) {
//            return getPrototypeInstance("subscriptionContext");
//        } else {
//            throw new UnsupportedOperationException("123");
//        }

}
