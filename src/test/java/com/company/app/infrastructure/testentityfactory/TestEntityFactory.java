package com.company.app.infrastructure.testentityfactory;

import com.company.app.infrastructure.testentityfactory.component.Context;
import com.company.app.infrastructure.testentityfactory.component.TestEntityFactoryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestEntityFactory {

    private final TestEntityFactoryRegistry testEntityFactoryRegistry;

    public Context createEntityContext() {
        return testEntityFactoryRegistry.getContext();
    }

}
