package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GCDTest {
    @Test
    void validateGCDTest(){
        GCD gcd = new GCD();
        assertAll(
                ()->assertEquals(28,gcd.getDivisor(616,364)),
                ()->assertEquals(123,gcd.getDivisor(123,0))
        );
    }

}
