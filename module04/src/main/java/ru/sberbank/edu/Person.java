package ru.sberbank.edu;

import java.util.Locale;
import java.util.Objects;

public class Person implements Comparable<Person>{

    private String name;
    private String city;
    private int age;

    @Override
    public int compareTo(Person other) {
        int result = this.city.compareTo(other.city);
        if (result == 0) {
            result = this.name.compareTo(other.name);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && name.equalsIgnoreCase(person.name) && city.equalsIgnoreCase(person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), city.toLowerCase(), age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }
}
