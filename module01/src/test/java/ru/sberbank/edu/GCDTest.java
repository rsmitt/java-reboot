package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GCDTest {

    @Test
    void whenFirstNumberGreaterSecondNumber() {
        GCD gcd = new GCD();
        int firstNumber = 345;
        int secondNumber = 126;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(3, divisor);
    }

    @Test
    void whenSecondNumberGreaterFirstNumber() {
        GCD gcd = new GCD();
        int firstNumber = 34567;
        int secondNumber = 76543;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(1, divisor);
    }

    @Test
    void whenFirstNumberNegative() {
        GCD gcd = new GCD();
        int firstNumber = -345;
        int secondNumber = 126;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(-3, divisor);
    }

    @Test
    void whenSecondNumberNegative() {
        GCD gcd = new GCD();
        int firstNumber = 987;
        int secondNumber = -126;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(-21, divisor);
    }

    @Test
    void whenBothNumberNegative() {
        GCD gcd = new GCD();
        int firstNumber = -987;
        int secondNumber = -567;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(-21, divisor);
    }

    @Test
    void whenFirstNumberIsZero() {
        GCD gcd = new GCD();
        int firstNumber = 0;
        int secondNumber = 126;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(0, divisor);
    }

    @Test
    void whenSecondNumberIsZero() {
        GCD gcd = new GCD();
        int firstNumber = 145;
        int secondNumber = 0;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(-1, divisor);
    }

    @Test
    void whenBothNumbersAreZero() {
        GCD gcd = new GCD();
        int firstNumber = 0;
        int secondNumber = 0;
        int divisor = gcd.getDivisor(firstNumber, secondNumber);

        Assert.assertEquals(-1, divisor);
    }
}