package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Юнит-тесты
 */
public class AppTest
{
    /**
     * Тестирование класса WeatherCache
     */
    @Test
    public void checkWeatherCache()
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        WeatherCache cache = context.getBean(WeatherCache.class);

        /**
         * Проверка, что при запросе "OMSK" возвращается непустое значение
         */
        System.out.println("=============================");
        WeatherInfo step_4 = cache.getWeatherInfo("OMSK");
        Assert.assertNotNull(step_4);
        System.out.println("=============================");

        /**
         * Проверка, что при запросе "qwerty" возвращается пустое значение
         */
        WeatherInfo step_5 = cache.getWeatherInfo("qwerty");
        Assert.assertNull(step_5);
        System.out.println("=============================");

    }
}
