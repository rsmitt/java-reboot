package ru.sberbank.edu;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase{

    @Test
    void main() throws IOException {
        StatisticImpl newStatistic = new StatisticImpl();
        newStatistic.start();
        Assertions.assertThat(newStatistic.getLongestLine()).isEqualTo("Мы добавляем третью базовую строку, но чтуь чуть подлинее");
        Assertions.assertThat(newStatistic.getSpaceCount()).isEqualTo(16);
        Assertions.assertThat(newStatistic.getLinesCount()).isEqualTo(3);
    }
}