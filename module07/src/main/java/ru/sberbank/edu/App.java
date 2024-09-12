package ru.sberbank.edu;

import ru.sberbank.edu.caches.WeatherCache;
import ru.sberbank.edu.provider.WeatherProvider;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        WeatherCache weatherCache = new WeatherCache(new WeatherProvider() );
        weatherCache.getWeatherInfo("London");
        System.out.println(weatherCache.getWeatherInfo("London"));

    }

}
