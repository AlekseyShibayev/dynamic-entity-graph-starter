package com.company.app.generator;

import com.google.common.collect.Maps;
import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class FunctionalInterfaceImplementationsCreator {

    public static Map<Class<?>, Object> createAllImplementationsByFunctionalInterfaceIfTheyInCommonPackage(Class<?> theirInterface) {
        Set<Class<?>> allImplementations = findAllImplementationsInPackageIfInterfaceInThisPackageToo(theirInterface);
        return createAndGet(allImplementations, theirInterface);
    }

    @SneakyThrows
    private static Set<Class<?>> findAllImplementationsInPackageIfInterfaceInThisPackageToo(Class<?> theirInterface) {
        return ClassPath.from(theirInterface.getClassLoader())
                .getAllClasses()
                .stream()
                .filter(classInfo -> classInfo.getPackageName().equalsIgnoreCase(theirInterface.getPackageName()))
                .map(classInfo -> classInfo.load())
                .filter(classFromPackage -> !classFromPackage.isInterface())
                .collect(Collectors.toSet());
    }

    @SneakyThrows
    private static Map<Class<?>, Object> createAndGet(Set<Class<?>> allImplementations, Class<?> theirInterface) {
        Method functionInterfaceMethod = getFunctionInterfaceMethod(theirInterface);

        Map<Class<?>, Object> result = Maps.newHashMap();
        for (Class<?> implementation : allImplementations) {
            Class<?> desireClassFromMethodSignature = getGenericClassFromMethodSignature(implementation, functionInterfaceMethod);

            Constructor<?>[] constructors = implementation.getConstructors();
            if (constructors.length == 1) {
                Constructor<?> defaultConstructor = constructors[0];
                result.put(desireClassFromMethodSignature, defaultConstructor.newInstance());
            }

        }
        return result;
    }

    private static Class<?> getGenericClassFromMethodSignature(Class<?> implementation, Method functionInterfaceMethod) {
        return Arrays.stream(implementation.getDeclaredMethods())
                .filter(method -> method.getName().equals(functionInterfaceMethod.getName()))
                .map(Method::getParameterTypes)
                .map(classes -> classes[0])
                .filter(desireClassFromMethodSignature -> desireClassFromMethodSignature != Object.class)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("bad settings for [%s]".formatted(implementation.getName())));
    }

    private static Method getFunctionInterfaceMethod(Class<?> theirInterface) {
        Method[] functionInterfaceMethods = theirInterface.getMethods();
        if (functionInterfaceMethods.length != 1) {
            throw new UnsupportedOperationException("[%s] - not a functional interface ".formatted(theirInterface.getName()));
        }
        return functionInterfaceMethods[0];
    }

}
