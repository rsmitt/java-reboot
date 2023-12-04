# Module 9. Spring Framework. XML Configuration. Configuration using annotations

### Требования:
1. Отрефакторить классы WeatherProvider, WeatherCache c помощью фреймворка Spring

2. Для конфигурирования использовать Java конфигурацию (предпочтение) или XML конфигурацию.
   Необходимо определить бины

3. Создать бин с типом RestTemplate

4. Создать бин, который реализует интерфейс WeatherProvider.

   - в данный бин необходимимо внедрить бин из пункта 3

   - в данный бин необходимимо внедрить свойство appKey. (key для сайта)
     Значение данного свойства поместить в файл src/main/resources/app.properties

5. Создать бин, который реализует интерфейс WeatherCache.

   - в данный бин необходимимо внедрить бин из пункта 4



## Критерии приемки:

1. Следующий код работает и выводит надпись GOOD! в консоль

```java
public static void main(String[] args) {
   ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

   WeatherCache cache = context.getBean(WeatherCache.class);

   WeatherInfo weatherInfo = cache.getWeatherInfo("OMSK");
   System.out.println("GOOD! weather=" + weatherInfo);
}
```

1. Проект должен собираться.
2. Класс должен быть протестирован с помощью JUnit.
3. Должны быть написаны java docs.
4. В репозитории не должно быть IDE-специфичных файлов.