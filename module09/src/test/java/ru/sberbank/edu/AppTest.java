package ru.sberbank.edu;

import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sberbank.edu.config.MyConfig;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static ApplicationContext context;
    private static WeatherCache cache;

    @BeforeAll
    public static void preTest() {
        context = new AnnotationConfigApplicationContext(MyConfig.class);
        cache = context.getBean(WeatherCache.class);
    }

    @Test
    @DisplayName("Тест метода isExpired")
    public void testIsExpired1() throws InterruptedException {
        AtomicReference<WeatherInfo> weatherInfo = new AtomicReference<>();
        Runnable task = () -> {
            try {
                weatherInfo.set(cache.getWeatherInfo("OMSK"));
                Thread.sleep(300_100);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        thread.join();

        Assertions.assertTrue(weatherInfo.get().isExpired());
    }

    @Test
    @DisplayName("Тест метода isExpired")
    public void testIsExpired2() throws InterruptedException {
        AtomicReference<WeatherInfo> weatherInfo = new AtomicReference<>();
        Runnable task = () -> {
            try {
                weatherInfo.set(cache.getWeatherInfo("OMSK"));
                Thread.sleep(250_000);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        thread.join();

        Assertions.assertFalse(weatherInfo.get().isExpired());
    }

    @Test
    @DisplayName("Тест на то, что возвращается null на несуществующий город")
    public void testGetWeatherInfo1() throws IOException {
        WeatherInfo weatherInfo = cache.getWeatherInfo("gsvhbfjhsdn");
        Assertions.assertNull(weatherInfo);
    }

    @Test
    @DisplayName("Тест на то, что возвращается объект на реальный город")
    public void testGetWeatherInfo2() throws IOException {
        WeatherInfo weatherInfo = cache.getWeatherInfo("Moscow");
        Assertions.assertNotNull(weatherInfo);
    }
}
