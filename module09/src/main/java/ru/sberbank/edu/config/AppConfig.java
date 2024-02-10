package ru.sberbank.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.edu.OpenWeatherProvider;
import ru.sberbank.edu.WeatherCache;
import ru.sberbank.edu.WeatherCacheImpl;
import ru.sberbank.edu.WeatherProvider;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public WeatherProvider getWeatherProvider(){
        return new OpenWeatherProvider();
    }

    @Bean
    public WeatherCache getWeatherCache(){
        return new WeatherCacheImpl();
    }

}
