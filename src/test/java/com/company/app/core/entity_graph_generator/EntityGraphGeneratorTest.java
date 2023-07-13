package com.company.app.core.entity_graph_generator;

import com.company.app.springboot.spring_boot_test.SpringBootTestApplicationContext;
import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
class EntityGraphGeneratorTest extends SpringBootTestApplicationContext {

    @SneakyThrows
    @Test
    void test1() {
        List<ClassPath.ClassInfo> list = ClassPath.from(Thread.currentThread().getContextClassLoader())
                .getAllClasses()
                .stream()
                .filter(classInfo -> isThisOurImplementation(classInfo.getClass()))
                .toList();

        log.debug("");
    }

    private boolean isThisOurImplementation(Class<?> anyClass) {
        return Arrays.stream(anyClass.getInterfaces())
                .anyMatch(aClass -> aClass.getClass().equals(EntityGraph.class));
    }

}