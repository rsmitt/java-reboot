package ru.sberbank.edu;
/**
 * @author bogdanzernovoj
 * @version 1.1
 * Класс содержащий информацию о человеке
 */
public class GreetingImpl implements Greeting{
    private final String hobby ;

    /**
     * Конструктор
     * @param hobby Увлечение
     */
    public GreetingImpl( String hobby ){
        this.hobby = hobby;
    }

    /**
     * Метод возвращает увлечение
     */
    @Override
    public String getBestHobby() {
        return this.hobby;
    }
}
