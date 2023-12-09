package ru.sberbank.edu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.WeatherCache;
import ru.sberbank.edu.WeatherProvider;

@Configuration
@PropertySource("classpath:app.properties")
public class MyConfig {


    @Value("${apiKey}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public WeatherProvider weatherProvider() {
        WeatherProvider weatherProvider = new WeatherProvider();
        weatherProvider.setApiKey(apiKey);
        return weatherProvider;
    }

    @Bean
    public WeatherCache weatherCache() {
        WeatherCache weatherCache = new WeatherCache();
        return weatherCache;
    }
}
