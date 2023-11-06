package ru.sberbank.edu;

import java.util.Objects;

public class Person implements Comparable<Person> {

    private final String name;
    private final String city;
    private final int age;

    /**
     * Конструктор класса Person
     * @param name - имя
     * @param city - город
     * @param age - возраст
     */
    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    /**
     * Получение имени
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получение города
     * @return город
     */
    public String getCity() {
        return city;
    }

    /**
     * Получение возраста
     * @return возраст
     */
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.age == person.age &&
                Objects.equals(this.name.toLowerCase(), person.name.toLowerCase()) &&
                Objects.equals(this.city.toLowerCase(), person.city.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, age);
    }


    @Override
    public int compareTo(Person person) {
        if (person == null) {
            throw new IllegalArgumentException();
        }
        if (this.city.equalsIgnoreCase(person.city)) {
            if (this.name.equalsIgnoreCase(person.name)) {
                return 0;
            } else {
                return this.name.toLowerCase().compareTo(person.name.toLowerCase());
            }
        } else {
            return this.city.toLowerCase().compareTo(person.city.toLowerCase());
        }
    }
}
