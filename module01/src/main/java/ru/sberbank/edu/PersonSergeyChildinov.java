package ru.sberbank.edu;

import java.util.Date;

/***
 * Класс содержит карочку Чильдинов Сергей Михайлович
 */

public class PersonSergeyChildinov implements Greeting{
    private static PersonSergeyChildinov instance;
    private final String lastName = "Чильдинов";
    private final String firstName = "Сергей";
    private final String secondName = "Михайлович";
    private final Date dateBirth = new Date(75, 8, 24);

    private final String bestHobby = "Чтение старинных книг";

    private final String city = "Санкт-Петербург";

    private final String experience = "SAP, ABAP";

    /***
     * Возвращает экземпляр класса PersonSergeyChildinov
     * @return экземпляр класса PersonSergeyChildinov
     */
    public  static PersonSergeyChildinov getInstance() {
        if (instance == null) {
            instance = new PersonSergeyChildinov();
        }
        return instance;
    }

    private PersonSergeyChildinov() {

    }

    public String getExperience() {
        return experience;
    }

    /***
     * Возвращает хобби
     * @return хобби
     */
    public String getBestHobby() {
        return bestHobby;
    }

    /***
     * Возвращает полное имя
     * @return полное имя
     */
    public String getFullName() {
        return lastName + " " + firstName + " "+ secondName;
    }

    /***
     * Возвращает город
     * @return город
     */
    public String getCity() {
        return city;
    }

    /***
     * Возвращает возраст в годах
     * @return возраст
     */
    public int getAge() {
        Date currentTime = new Date();
        long msTimeDistance = currentTime.getTime() - dateBirth.getTime();
        long msDays = 24 * 60 * 60 * 1000;

        return (int) (msTimeDistance/msDays/365);
    }

    @Override
    public String toString() {
        return "Имя : "+ getFullName()+
                "\n Город : "+ getCity() +
                "\n Возраст (приблизительно) : " + getAge() +
                "\n Опыт в прогаммировании : " + getExperience() +
                "\n Хобби : " + getBestHobby();
    }
}