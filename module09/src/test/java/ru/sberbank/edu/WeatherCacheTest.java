package ru.sberbank.edu;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WeatherCacheTest {

    @Test
    void getWeatherInfo() {
        final String CITY = "Москва";
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        WeatherCache cache = context.getBean(WeatherCache.class);
        WeatherInfo info1 = cache.getWeatherInfo(CITY);
        assertEquals(CITY, info1.getCity());
        WeatherInfo info2 = cache.getWeatherInfo(CITY);
        assertEquals(CITY, info2.getCity());
        assertEquals(info1.getExpiryTime(), info2.getExpiryTime());
        WeatherInfo info3 = cache.getWeatherInfo("asasas");
        assertNull(info3);
    }

}