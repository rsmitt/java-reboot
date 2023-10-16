package ru.sberbank.edu;

import org.junit.Assert;

import org.junit.jupiter.api.Test;


class GCDTest {

    @Test
    public void getDivisor() {
        GCD gcd = new GCD();
        Assert.assertEquals(100, gcd.getDivisor(100, 200));
        Assert.assertEquals(0, gcd.getDivisor(0, 0));
        Assert.assertEquals(5, gcd.getDivisor(10, 5));
        Assert.assertEquals(12, gcd.getDivisor(36, 12));
    }
}