package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class GreetingImplTest {

   @Test
    void whenHobbyCheckIsTrue() {
       GreetingImpl greeting = new GreetingImpl("Писать код", new Student("Иван", "Иванов"));
        String expected = "Писать код";
        String result = greeting.getBestHobby();
       Assert.assertEquals(expected, result);
    }

    @Test
    void whenHobbyCheckIsFalse() {
        GreetingImpl greeting = new GreetingImpl("Писать код", new Student("Иван", "Иванов"));
        String expected = "Нет хобби";
        String result = greeting.getBestHobby();

        Assert.assertFalse(expected.equals(result));
    }

    @Test
    void whenStudentCheckIsTrue() {
        GreetingImpl greeting = new GreetingImpl("Писать код", new Student("Иван", "Иванов"));
        String expected = "Студент: Иван Иванов";
        String result = greeting.getStudent().toString();

        Assert.assertEquals(expected, result);
    }

    @Test
    void whenStudentCheckIsFalse() {
        GreetingImpl greeting = new GreetingImpl("Писать код", new Student("Иван", "Иванов"));
        String expected = "Студент: Иван";
        String result = greeting.getStudent().toString();

        Assert.assertFalse(expected.equals(result));
    }
}