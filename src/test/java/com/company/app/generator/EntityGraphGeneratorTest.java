package com.company.app.generator;

import com.company.app.springboot.spring_boot_test.SpringBootTestApplicationContext;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EntityGraphGeneratorTest extends SpringBootTestApplicationContext {

    @Autowired
    private Generator generator;

    @SneakyThrows
    @Test
    void test1() {
        generator.doMainLogic();
    }

}