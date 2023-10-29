package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GCDTest {

    @Test
    void getDivisor() {
        GCD gcd = new GCD();
        Assertions.assertEquals(gcd.getDivisor(144,24),24);
        Assertions.assertEquals(gcd.getDivisor(143,24),1);
        Assertions.assertEquals(gcd.getDivisor(15,6),3);
    }
}