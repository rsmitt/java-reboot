package ru.sberbank.edu;

/**
 * Запуск таска в потоке
 *
 */
public class App
{
    public static void main( String[] args ) {

        WeatherCache wc = new WeatherCache(new WeatherProvider());

        Runnable task = () -> {
            try {
                System.out.println("=============================");
                // Кидаем запрос в API на получение погоды в Ростове.
                wc.getWeatherInfo("Rostov-on-Don");
                // Засыпаем на минуту.
                System.out.println("Thread fell asleep for 60 seconds...");
                Thread.sleep(60000);
                System.out.println("=============================");
                // Просыпаемся. Кэш еще актуален.
                System.out.println("Thread woke up!");
                // Запрашиваем погоду из кэша.
                wc.getWeatherInfo("Rostov-on-Don");
                // Засыпаем на 6 минут, чтобы кэш "протух".
                System.out.println("Thread fell asleep for 360 seconds...");
                Thread.sleep(360000);
                System.out.println("=============================");
                // Просыпаемся. Кэш уже не актуален.
                System.out.println("Thread woke up!");
                // Кидаем запрос в API на получение актуальной погоды в Ростове.
                wc.getWeatherInfo("Rostov-on-Don");
                // Снова засыпаем на 10 секунд, просто чтобы отдохнуть немного)
                System.out.println("Thread fell asleep for 10 seconds...");
                Thread.sleep(10000);
                System.out.println("=============================");
                // Просыпаемся. И запрашиваем у API погоду в несуществующем городе "qwerty".
                System.out.println("Thread woke up!");
                wc.getWeatherInfo("qwerty");
                // Возвращает ответ, что наш запрос был некорректным.
                System.out.println("=============================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread myThread = new Thread(task);
        myThread.start();

    }
}