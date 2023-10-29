package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.impl.GreetingImpl;

public class GreetingImplTest {

    private final Greeting greeting = new GreetingImpl();

    @Test
    public void positiveTest() {
        Assertions.assertEquals("Basketball", greeting.getBestHobby());
    }

    @Test
    public void negativeTest() {
        Assertions.assertNotEquals("Football", greeting.getBestHobby());
    }

}
