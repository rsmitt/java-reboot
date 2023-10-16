package ru.sberbank.edu;

public class Person {
    String name;
    String surname;
    String age;

    Person(String name, String surname, String age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(String s) {
        this.surname = s;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setAge(String a) {
        this.age = a;
    }

    public String getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return "Name=" + name
                + ", Surname=" + surname
                + ", Age=" + age + "]";
    }
}
