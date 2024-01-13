package ru.sberbank.edu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sberbank.edu.config.AppConfig;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WeatherCache cache = context.getBean(WeatherCache.class);

        WeatherInfo info = cache.getWeatherInfo("Moscow");

        System.out.println(info);
    }
}
