package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPerson {

    @Test
    public void testPerson() {

    Person person1 = new Person("Oleg", "Moscow", 15);
    Person person2 = new Person("Egor", "Kasimov", 16);
    Person person3 = new Person("Egor", "KaSImov", 16);

    Assertions.assertEquals(person1,person1,"Ошибка эквивалентности одного и того же объекта");
    Assertions.assertNotEquals(person1,person2,"Ошибка эквивалентности объектов");
    Assertions.assertEquals(person2,person3,"Ошибка эквивалентности объектов");

    Assertions.assertEquals(person1.hashCode(),person1.hashCode(),"Ошибка генерации hash");
    Assertions.assertNotEquals(person1.hashCode(),person2.hashCode(),"Ошибка генерации hash");
    Assertions.assertEquals(person2.hashCode(),person3.hashCode(),"Ошибка генерации hash");
    }
    @Test
    public void testCompareTo(){

        List<Person> persons = new ArrayList<>();

        Person person1 = new Person("Oleg", "Moscow", 15);
        Person person2 = new Person("Oleg", "Anapa", 15);
        Person person3 = new Person("Egor", "Kasimov", 16);
        Person person4 = new Person("Egor", "KaSImov", 16);

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);

        Assertions.assertEquals(person1,persons.get(0),"Ошибка сортировки");
        persons.sort(Person::compareTo);
        Assertions.assertEquals(person2,persons.get(0),"Ошибка сортировки");
    }
}
