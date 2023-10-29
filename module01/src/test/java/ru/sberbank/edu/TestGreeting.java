package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class TestGreeting {

    @Test
    @DisplayName("Тест класса GreetingImpl на то, что он реализует только один интерфейс Greeting")
    public void test_interface() {
        GreetingImpl greeting = new GreetingImpl();
        Class<?> clz = greeting.getClass();
        Class<?>[] interfaces = clz.getInterfaces();
        Assertions.assertTrue(interfaces.length == 1 && interfaces[0].equals(Greeting.class));
    }
    @Test
    @DisplayName("Тест класса GreetingImpl на то, что метод getBestHobby единственный и возвращает тип String")
    public void test_method() {
        GreetingImpl greeting = new GreetingImpl();
        Class<?> clz = greeting.getClass();
        Method[] methods = clz.getDeclaredMethods();
        Assertions.assertTrue(methods.length == 1 && methods[0].getReturnType().equals(String.class));
    }

    @Test
    public void test_output() {
        GreetingImpl greeting = new GreetingImpl();
        Assertions.assertEquals("java.lang.String", greeting.getBestHobby().getClass().getTypeName());
    }
}
