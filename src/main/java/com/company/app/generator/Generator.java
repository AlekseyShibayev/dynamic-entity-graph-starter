package com.company.app.generator;

import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Generator {

    @SneakyThrows
    void doMainLogic() {
        Set<Class<?>> set = ClassPath.from(this.getClass().getClassLoader())
                .getAllClasses()
                .stream()
                .filter(anyClass -> isThisOurImplementation(anyClass))
                .map(classInfo -> classInfo.load())

                .collect(Collectors.toSet());

        log.debug("");
    }

    private boolean isThisOurImplementation(ClassPath.ClassInfo classInfo) {
        if (classInfo.getName().contains("entity_graph_generator")) {
            return true;
        }
        return false;
//        return Arrays.stream(anyClass.getInterfaces())
//                .anyMatch(aClass -> aClass.getClass().equals(EntityGraph.class));
    }

}
