package ru.sberbank.edu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sberbank.edu.config.MyConfig;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        WeatherCache cache = context.getBean(WeatherCache.class);

        WeatherInfo weatherInfo = cache.getWeatherInfo("OMSK");
        System.out.println("GOOD! weather=" + weatherInfo);
    }
}
