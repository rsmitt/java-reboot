package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        WeatherProvider weatherProvider = new WeatherProvider();
        WeatherCache weatherCache = new WeatherCache(weatherProvider);
        Runnable task = () -> {
            System.out.println(weatherCache.getWeatherInfo("Moscow"));
            System.out.println("Task thread=" + getThreadInfo());
        };
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
        System.out.println("Main thread=" + getThreadInfo());
    }

    private static String getThreadInfo() {
        Thread thread = Thread.currentThread();
        long id = thread.getId();
        String name = thread.getName();
        return "Thread{id=" + id + " name=" + name + "}";
}
}
