package ru.sberbank.edu;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WeatherProviderTest {
    @Test
    public void WhenPositiveScenario() throws InterruptedException {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        WeatherInfo actualResult = weatherCache.getWeatherInfo("Moscow");
        assertEquals("Moscow", actualResult.getCity());
        assertTrue(actualResult.getTemperature() > 0);
    }

    @Test
    public void WhenCacheContainsActualInfo() throws InterruptedException {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        LocalDateTime actualResult = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
        TimeUnit.SECONDS.sleep(5);
        LocalDateTime expectedResult = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
        assertTrue(actualResult.equals(expectedResult));
    }

    @Test
    public void WhenCacheNotContainsActualInfo() throws InterruptedException {
        //ДОДЕЛАТЬ
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        LocalDateTime actualResult = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
        TimeUnit.SECONDS.sleep(5);
        LocalDateTime expectedResult = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
        assertTrue(actualResult.equals(expectedResult));
    }
}
