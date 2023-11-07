package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>Тестовый класс для методов StatisticImplImpl</p>
 */
class StatisticImplTest {
    StatisticImpl statistic = null;

    /**
     * <p>Инициализация объектов и переменных перед тестом</p>
     *
     * @throws Exception
     */
    @BeforeEach
    public void setup() throws Exception {
        statistic = new StatisticImpl("src/test/test.txt");
    }

    @Test
    void testGetLineCount() {
        assertEquals(4, statistic.getLineCount());
    }

    @Test
    void getSpaceCount() {
        assertEquals(12, statistic.getSpaceCount());
    }

    @Test
    void getLongestLine() {
        assertEquals("Какая строка интересно самая длинная? Наверное текущая", statistic.getLongestLine());
    }
}