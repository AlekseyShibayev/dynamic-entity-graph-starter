package com.company.app.infrastructure.testentityfactory;

import com.company.app.infrastructure.testentityfactory.component.FirstContext;
import com.company.app.infrastructure.testentityfactory.component.TestEntityFactoryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestEntityFactory {

    private final TestEntityFactoryRegistry testEntityFactoryRegistry;

    public FirstContext createFirstEntityContext() {
        return testEntityFactoryRegistry.getFirstContext();
    }

}
