package ru.sberbank.edu;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("application.properties")
@ComponentScan(basePackages = "ru.sberbank.edu")
public class MyConfig {
    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}