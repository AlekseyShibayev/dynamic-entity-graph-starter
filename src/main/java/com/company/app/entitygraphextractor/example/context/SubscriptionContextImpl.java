package com.company.app.entitygraphextractor.example.context;

import com.company.app.entitygraphextractor.domain.entity.Subscription;
import com.company.app.entitygraphextractor.example.common.AbstractContext;
import com.company.app.entitygraphextractor.example.common.Node;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SubscriptionContextImpl extends SubscriptionContext<Subscription> {

    @Override
    public Class<Subscription> getClass_() {
        return Subscription.class;
    }

    @Override
    public Long getId_() {
        return entity.getId();
    }

    public SubscriptionContextImpl withChats() {
        nodes.add(Node.builder()
                .name("chats")
                .build());
        return this;
    }

}
