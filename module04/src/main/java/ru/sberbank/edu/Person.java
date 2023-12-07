package ru.sberbank.edu;

import java.util.Objects;

public class Person implements Comparable<Person> {

    String name, city;
    Integer age;

    public Person(String name, String city, Integer age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    /**
     * Сортировка сначала по полю `city`, а затем по полю `name`.
     */
    @Override
    public int compareTo(Person p) {

        if (this.city.compareToIgnoreCase(p.city) == 0) {
            return this.name.compareToIgnoreCase(p.name);
        } else {
            return this.city.compareToIgnoreCase(p.city);
        }
    }

    /**
     * Условие равенства: все поля `name`, `city`, `age` должны совпадать, `name` и `city` без учета регистра).
     */
    public boolean equals(Person p) {

        return p.name.equalsIgnoreCase(this.name) &&
                p.city.equalsIgnoreCase(this.city) &&
                p.age.equals(this.age);
    }

    /**
     * Получение hashCode по полям name, city, age без учета регистра
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name.toLowerCase(), this.city.toLowerCase(), this.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

}
