package ru.sberbank.edu;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WeatherProviderTest {
    @Test
    public void WhenPositiveScenario() {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        WeatherInfo actualResult = weatherCache.getWeatherInfo("Moscow");
        assertEquals("Moscow", actualResult.getCity());
        assertTrue(actualResult.getTemperature() > 0);
    }

    @Test
    public void WhenCacheContainsActualInfo() {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        LocalDateTime actualResult = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
        LocalDateTime expectedResult = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
        assertTrue(actualResult.equals(expectedResult));
    }

    @Test
    public void WhenCacheNotContainsActualInfo(){
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        LocalDateTime data = LocalDateTime.now().minusMinutes(6);
        LocalDateTime cacheData;
        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(() -> LocalDateTime.now()).thenReturn(data);
            cacheData = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
            System.out.println(cacheData);
        }
        LocalDateTime newCacheData = weatherCache.getWeatherInfo("Moscow").getExpiryTime();
        assertTrue((newCacheData.toEpochSecond(ZoneOffset.UTC) - cacheData.toEpochSecond(ZoneOffset.UTC)) > 300);
    }
}
