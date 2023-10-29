package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.impl.GCD;

public class GCDTest {

    private final CommonDivisor commonDivisor = new GCD();

    @Test
    public void gcdTest() {
        Assertions.assertEquals(12, commonDivisor.getDivisor(300, 108));
        Assertions.assertEquals(12, commonDivisor.getDivisor(-300, 108));
        Assertions.assertEquals(0, commonDivisor.getDivisor(0, 0));
        Assertions.assertEquals(1, commonDivisor.getDivisor(1, 12));
        Assertions.assertEquals(12, commonDivisor.getDivisor(0, 12));
        Assertions.assertEquals(0, commonDivisor.getDivisor(-0, 0));
    }
}
