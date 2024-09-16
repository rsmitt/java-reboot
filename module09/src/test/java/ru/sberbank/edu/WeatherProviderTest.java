package ru.sberbank.edu;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class WeatherProviderTest {
    @Test
    public void WhenPositiveScenario() {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.sberbank.edu");
        WeatherCache cache = context.getBean(WeatherCache.class);
        WeatherInfo actualResult = cache.getWeatherInfo("OMSK");
        assertEquals("OMSK", actualResult.getCity());
        assertTrue(actualResult.getTemperature() > 0);
    }

    @Test
    public void WhenCacheContainsActualInfo() throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.sberbank.edu");
        WeatherCache cache = context.getBean(WeatherCache.class);
        LocalDateTime actualResult = cache.getWeatherInfo("Moscow").getExpiryTime();
        TimeUnit.SECONDS.sleep(1);
        LocalDateTime expectedResult = cache.getWeatherInfo("Moscow").getExpiryTime();
        assertTrue(actualResult.equals(expectedResult));
    }

    @Test
    public void WhenCacheNotContainsActualInfo(){
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.sberbank.edu");
        WeatherCache cache = context.getBean(WeatherCache.class);
        LocalDateTime data = LocalDateTime.now().minusMinutes(6);
        LocalDateTime cacheData;
        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(() -> LocalDateTime.now()).thenReturn(data);
            cacheData = cache.getWeatherInfo("Moscow").getExpiryTime();
            System.out.println(cacheData);
        }
        LocalDateTime newCacheData = cache.getWeatherInfo("Moscow").getExpiryTime();
        assertTrue((newCacheData.toEpochSecond(ZoneOffset.UTC) - cacheData.toEpochSecond(ZoneOffset.UTC)) > 300);
    }

}
