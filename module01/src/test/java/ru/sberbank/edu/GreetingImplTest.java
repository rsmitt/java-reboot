package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.Test;


public class GreetingImplTest {
    /***
     * getBestHobby must return a string
     * and the information should be about me
     */
    @Test
    public void GreetingTest() {
        GreetingImpl greeting = new GreetingImpl();
        Assert.assertNotNull(greeting.getBestHobby());
        Assert.assertEquals("my hobby is learning Java",greeting.getBestHobby());
    }

}