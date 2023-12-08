package ru.sberbank.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.WeatherCache;
import ru.sberbank.edu.WeatherProvider;

@Configuration
public class MyConfig {


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public WeatherProvider weatherProvider() {
        WeatherProvider weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    @Bean
    public WeatherCache weatherCache() {
        WeatherCache weatherCache = new WeatherCache();
        return weatherCache;
    }
}
