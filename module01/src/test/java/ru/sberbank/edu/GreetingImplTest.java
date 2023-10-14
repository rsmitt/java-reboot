package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingImplTest {
    @Test
    void validateGettersTest() {
        GreetingImpl greetingImpl = new GreetingImpl("Alesandr", "Kanarskiy", "Snorking");
        assertAll(
                () -> assertEquals("Alesandr", greetingImpl.getName()),
                () -> assertEquals("Kanarskiy", greetingImpl.getLastName()),
                () -> assertEquals("Snorking", greetingImpl.getBestHobby())
        );

    }

}
