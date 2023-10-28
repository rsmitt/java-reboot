package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.Test;

public class GreetingImplTest {
    @Test
    public void testHobby() {
        GreetingImpl loGreeting = new GreetingImpl("Плавать");
        Assert.assertSame("Check Swimming", "Плавать", loGreeting.getBestHobby());
    }
}
