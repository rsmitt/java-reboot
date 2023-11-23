package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WeatherProvider weatherProvider = new WeatherProvider();
        weatherProvider.get("Moscow");
    }
}
