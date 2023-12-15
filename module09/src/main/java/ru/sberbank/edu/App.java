package ru.sberbank.edu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Основной класс для запуска потока
 */
public class App
{
    public static void main( String[] args ) {

        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        WeatherCache cache = context.getBean(WeatherCache.class);

        /**
         * Подготовка задачи
         */
        Runnable task = () -> {
            try {
                System.out.println("=============================");
                WeatherInfo step_1 = cache.getWeatherInfo("Rostov-on-Don");
                System.out.println("Thread fell asleep for 60 seconds...");
                Thread.sleep(60000);
                System.out.println("=============================");
                System.out.println("Thread woke up!");
                WeatherInfo step_2 = cache.getWeatherInfo("Rostov-on-Don");
                System.out.println("Check for true = " + step_2.toString().equalsIgnoreCase(step_1.toString()));
                System.out.println("Thread fell asleep for 360 seconds...");
                Thread.sleep(360000);
                System.out.println("=============================");
                System.out.println("Thread woke up!");
                WeatherInfo step_3 = cache.getWeatherInfo("Rostov-on-Don");
                System.out.println("Check for true = " + !step_3.toString().equalsIgnoreCase(step_1.toString()));
                System.out.println("Thread fell asleep for 10 seconds...");
                Thread.sleep(10000);
                System.out.println("=============================");
                System.out.println("Thread woke up!");
                WeatherInfo step_4 = cache.getWeatherInfo("OMSK");
                System.out.println("GOOD! weather=" + step_4);
                System.out.println("Check for true = " + (step_4 != null));
                System.out.println("Thread fell asleep for 10 seconds...");
                Thread.sleep(10000);
                System.out.println("=============================");
                System.out.println("Thread woke up!");
                WeatherInfo step_5 = cache.getWeatherInfo("qwerty");
                System.out.println("Check for true = " + (step_5 == null));
                System.out.println("=============================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        /**
         * Запуск задачи в потоке myThread
         */
        Thread myThread = new Thread(task);
        myThread.start();

    }
}
