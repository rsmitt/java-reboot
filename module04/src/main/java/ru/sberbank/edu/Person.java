package ru.sberbank.edu;

import java.util.Arrays;

public class Person implements Comparable<Person> {

    private String name;
    private String city;
    private int age;

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Поле name не может быть null");
        }
        this.name = name;
    }

    public void setCity(String city) {
        if (city == null) {
            throw new IllegalArgumentException("Поле city не может быть null");
        }
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
        setName(name);
        setCity(city);
        setAge(age);
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
        if (this == o) {
            return true;
        }
        if (!o.getClass().equals(Person.class)) {
            return false;
        }
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                getName().equalsIgnoreCase(person.getName()) &&
                getCity().equalsIgnoreCase(person.getCity());
    }

    /**
     Хэш-функция для строк методом Горнера
     */

    private int hashFunction(String str, int p, int size) {
        int prime = 1;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash += prime * ((int) str.charAt(i));
            prime *= p;
            hash = hash % size;
        }
        return hash;
    }

    @Override
    public int hashCode() {
        int p = 2;
        int size = 23; // простое число
        return (hashFunction(getName(), p, size) + hashFunction(getCity(), p, size) + getAge()) % size;
    }

    @Override
    public int compareTo(Person o) {
        if (getCity().equalsIgnoreCase(o.getCity()) && getName().equalsIgnoreCase(o.getName())) {
            return Integer.compare(getAge(), o.getAge());
        } else {
            String[] cities1 = new String[] {getCity().toLowerCase(), o.getCity().toLowerCase()};
            String[] cities2 = new String[] {getCity().toLowerCase(), o.getCity().toLowerCase()};
            String[] names1 = new String[] {getName().toLowerCase(), o.getName().toLowerCase()};
            String[] names2 = new String[] {getName().toLowerCase(), o.getName().toLowerCase()};
            Arrays.sort(cities1);
            Arrays.sort(names1);
            if (!cities1[0].equals(cities2[0])) {
                return 1;
            } else {
                if (!getCity().equalsIgnoreCase(o.getCity())) {
                    return -1;
                } else {
                    if (!names1[0].equals(names2[0])) {
                        return 1;
                    } else {
                        if (!getName().equalsIgnoreCase(o.getName())) {
                            return -1;
                        } else {
                            return Integer.compare(getAge(), o.getAge());
                        }
                    }
                }
            }
        }
    }
}
