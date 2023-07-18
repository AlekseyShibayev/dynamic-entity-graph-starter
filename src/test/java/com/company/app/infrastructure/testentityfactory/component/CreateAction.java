package com.company.app.infrastructure.testentityfactory.component;

@FunctionalInterface
public interface CreateAction {

    void doEnrich(FirstContext context);

}
