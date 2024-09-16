package test;


import org.testng.annotations.Test;
import static org.junit.Assert.*;

import ru.sberbank.edu.GCD;


public class GCDTest {
    @Test
    public void WhenGetDivisor100and75Then25() {
        GCD gcd = new GCD();
        int ExpectedResult = 25;
        assertEquals(ExpectedResult, gcd.getDivisor(100, 75));
    }

    @Test
    public void WhenGetDivisor795and70Then25() {
        GCD gcd = new GCD();
        int ExpectedResult = 5;
        assertEquals(ExpectedResult, gcd.getDivisor(795, 70));
    }

    @Test
    public void WhenGetDivisor0and3Then25() {
        GCD gcd = new GCD();
        int ExpectedResult = 3;
        assertEquals(ExpectedResult, gcd.getDivisor(0, 3));
    }

    @Test
    public void WhenGetDivisor300and300Then25() {
        GCD gcd = new GCD();
        int ExpectedResult = 300;
        assertEquals(ExpectedResult, gcd.getDivisor(300, 300));
    }

}
