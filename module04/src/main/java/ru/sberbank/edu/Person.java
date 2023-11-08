package ru.sberbank.edu;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private String name;
    private String city;

    private int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, age);
    }

    @Override
    public int compareTo(Person o) {
        if(this.city == null || o.city == null || this.name == null || o.name == null ){
            throw new RuntimeException();
        }
        if (this.city.equalsIgnoreCase(o.city)){
            if(this.name.equalsIgnoreCase(o.name)){
                return 0;
            }else if (this.name.compareToIgnoreCase(o.name) > 0){
                return 1;
            }else{
            return -1;
        }
        }
        if (this.city.compareTo(o.city) < 0) {
            return -1;
        }
        else
            return 1;
    }
}

