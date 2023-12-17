package ru.sberbank.edu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.WeatherCache;
import ru.sberbank.edu.WeatherCacheComponent;
import ru.sberbank.edu.WeatherProvider;
import ru.sberbank.edu.WeatherProviderComponent;

@Configuration
@PropertySource("classpath:app.properties")
public class MyConfig {


    @Value("${appKey}")
    String appKey;

    @Bean
    String getAppKey(){
        return appKey;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WeatherProvider getWeatherProvider() {
        return new WeatherProviderComponent();
    }

    @Bean
    public WeatherCache getWeatherCache(WeatherProvider weatherProvider) {
        return new WeatherCacheComponent(weatherProvider);
    }
}
