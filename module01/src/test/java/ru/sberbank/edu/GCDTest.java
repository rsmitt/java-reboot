package ru.sberbank.edu;

import junit.framework.TestCase;

import java.util.Random;
/**
 * <p>Тестовый класс для GCD</p>
 */
public class GCDTest extends TestCase {
    /**
     * <p>Тест метода, вычисляющего НОД</p>
     */
    public void testGetDivisor() {
        assertEquals(6, new GCD().getDivisor(4218954,54846414));
    }

    /**
     * <p>Тест метода, вычисляющего НОД, на случайных числах</p>
     */
    public void testGetDivisorRandom() {
        Random random = new Random();
        int a = random.nextInt(Integer.MAX_VALUE);
        int b = random.nextInt(Integer.MAX_VALUE);
        assertEquals(GCDCalc(a,b), new GCD().getDivisor(a,b));
    }
    private int GCDCalc(int a, int b){
                while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }
}