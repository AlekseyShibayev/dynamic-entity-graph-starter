package com.company.app.entitygraphextractor.example.context;

import com.company.app.entitygraphextractor.example.common.AbstractContext;

public abstract class ChatContext<E> extends AbstractContext<E> {


    public abstract ChatContextImpl withUserInfo();

    public abstract ChatContextImpl withHistories();

    public abstract ChatContextImpl withSubscriptions();

    public abstract ChatContextImpl withSubscriptionsAndChats();

    public abstract ChatContextImpl withSubscriptionsAndSubscriptionInfos();

}
