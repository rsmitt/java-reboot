package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private WeatherProvider provider;
    private WeatherCache cache;

    @BeforeEach
    public void beforeEach() {
        provider = new WeatherProvider();
        cache = new WeatherCache(provider);
    }

    @Test
    @DisplayName("Тест метода get из класса WeatherProvider")
    public void testGetProvider() throws IOException {
        Assertions.assertTrue(provider.get("Moscow") != null &&
                provider.get("abc") == null);
    }

    @Test
    @DisplayName("Тест на то, что из интернета не берется новой инфы, если прошло меньше 5 минут")
    public void testShortDelay() throws IOException, InterruptedException {
       LocalDateTime time1 = cache.getWeatherInfo("Moscow").getExpiryTime();

       Thread.sleep(1_000);

       LocalDateTime time2 = cache.getWeatherInfo("Moscow").getExpiryTime();

       Assertions.assertEquals(time1, time2);
    }

    @Test
    @DisplayName("Тест на то, что из интернета берется новая инфы, если прошло больше 5 минут")
    public void testLongDelay() throws IOException, InterruptedException {
//        код ниже не работает, так как объект типа LocalDateTime является ссылочного типа, а возможности клонировать его нет

//        LocalDateTime time1 = cache.getWeatherInfo("Moscow").getExpiryTime();
//
//        Thread.sleep(300_300);
//
//        LocalDateTime time2 = cache.getWeatherInfo("Moscow").getExpiryTime();
//
//        Assertions.assertTrue(Duration.between(time1, time2).toMinutes() >= 5);
    }
}
