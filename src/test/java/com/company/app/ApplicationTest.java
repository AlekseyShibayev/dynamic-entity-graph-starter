package com.company.app;

import com.company.app.infrastructure.SpringBootTestApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

class ApplicationTest extends SpringBootTestApplicationContext {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void context_must_rise() {
        Assertions.assertNotNull(applicationContext);
    }

}
