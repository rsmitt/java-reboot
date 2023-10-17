package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Это Юнит-тест для проверки алгоритма Евклида, для поиска наименьшего общего кратного.
 */
public class GSDTest
{
    /**
     * Это тело самого теста. Реализация метода представлена в файле GSD.java
     */
    @Test
    public void testEvklid()
    {
        GSD gsd = new GSD();

        Assertions.assertEquals(gsd.getDivisor(8, 6), 2);
        Assertions.assertEquals(gsd.getDivisor(9, 3), 3);
        Assertions.assertEquals(gsd.getDivisor(10, 5), 5);

    }
}
