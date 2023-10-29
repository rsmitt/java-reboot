package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class TestGCD {

    @Test
    @DisplayName("Тест класса GCD на то, что реализует 1 интерфейс CommonDivisor")
    public void test_interface() {
        GCD gcd = new GCD();
        Class<?> clz = gcd.getClass();
        Class<?>[] interfaces = clz.getInterfaces();
        Assertions.assertTrue(interfaces.length == 1 && interfaces[0].equals(CommonDivisor.class));
    }

    @Test
    @DisplayName("Тест класса GCD на то, что он содержит 1 метод getDivisor, который содержит лишь 2 int-аргумента и возвращает тоже int")
    public void test_method_args() {
        GCD gcd = new GCD();
        Class<?> clz = gcd.getClass();
        Method[] methods = clz.getDeclaredMethods();
        Assertions.assertTrue(methods.length == 1 && methods[0].getReturnType().equals(int.class)
                && methods[0].getParameterCount() == 2 && methods[0].getParameterTypes()[0].equals(int.class)
                && methods[0].getParameterTypes()[1].equals(int.class));
    }

    @Test
    @DisplayName("Тест класса GCD на математику")
    public void test_math() {
        GCD gcd = new GCD();
        Assertions.assertTrue(gcd.getDivisor(0, 0) == 0 && gcd.getDivisor(-2, 6) == 2
                && gcd.getDivisor(13, 24) == 1 && gcd.getDivisor(15, 15) == 15
                && gcd.getDivisor(0, 43) == 43);
    }
}
