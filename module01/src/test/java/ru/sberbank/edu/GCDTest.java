package ru.sberbank.edu;
import org.junit.Assert;
import org.junit.Test;
public class GCDTest {
    @Test
    public void testDivisor() {
        CommonDivisor loGCD = new GCD( );
        Assert.assertEquals("Ошибка определения делителя", 4, loGCD.getDivisor(12, 16));
        Assert.assertEquals("Ошибка определения делителя", 15, loGCD.getDivisor(0, 15));
    }


}
