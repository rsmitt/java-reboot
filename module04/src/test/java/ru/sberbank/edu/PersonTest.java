package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonTest {

    @Test
    public void whenCreateConstructor() {
        Person person = new Person("Artem", "Ekb", 31);
        Assertions.assertEquals("Artem", person.getName());
        Assertions.assertEquals("Ekb", person.getCity());
        Assertions.assertEquals(31, person.getAge());
    }

    @Test
    public void whenCallToString() {
        Person person = new Person("Artem", "Ekb", 31);
        Assertions.assertEquals("Person{name='Artem', city='Ekb', age=31}", person.toString());
    }

    @Test
    public void whenBothObjectIsEquals() {
        Person person1 = new Person("Artem", "Ekb", 31);
        Person person2 = new Person("Artem", "Ekb", 31);
        Assertions.assertEquals(person1, person2);
        Person person3 = new Person("artem", "ekb", 31);
    }

    @Test
    public void whenBothObjectLinkIsEquals() {
        Person person1 = new Person("Artem", "Ekb", 31);
        Person person2 = person1;
        Assertions.assertEquals(person1, person2);
    }

    @Test
    public void whenBothObjectIsNotEquals() {
        Person person1 = new Person("Artem", "Ekb", 31);
        Person person2 = new Person("Misha", "Omsk", 27);
        Assertions.assertNotEquals(person1, person2);
        Person person3 = new Person("Artem", "Ekb", 13);
        Assertions.assertNotEquals(person1, person3);
        Person person4 = new Person("Artem", "Msk", 31);
        Assertions.assertNotEquals(person1, person4);
        Person person5 = new Person("Rodion", "Ekb", 31);
        Assertions.assertNotEquals(person1, person5);
    }

    @Test
    public void whenBothHasDifferentClass() {
        Person person1 = new Person("Artem", "Ekb", 31);
        String person2 = "Misha, Omsk, 27";
        Assertions.assertNotEquals(person1, person2);
    }

    @Test
    public void whenBothObjectHasEqualsHashcode() {
        Person person1 = new Person("Artem", "Ekb", 31);
        Person person2 = new Person("Artem", "Ekb", 31);
        Assertions.assertTrue(person1.equals(person2) && person2.equals(person1));
        Assertions.assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void whenBothObjectHasNotEqualsHashcode() {
        Person person1 = new Person("Artem", "Ekb", 31);
        Person person2 = new Person("artem", "ekb", 31);
        Assertions.assertTrue(person1.equals(person2) && person2.equals(person1));
        Assertions.assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void whenSortedPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Misha", "Omsk", 27));
        personList.add(new Person("Sasha", "Omsk", 35));
        personList.add(new Person("Stas", "Moscow", 25));
        personList.add(new Person("Artem", "Ekaterinburg", 31));
        personList.add(new Person("Artem", "Ekaterinburg", 30));
        personList.add(new Person("Yulia", "Moscow", 35));
        personList.add(new Person("Kostya", "Novosibirsk", 32));
        personList.add(new Person("Katya", "Novosibirsk", 30));
        Collections.sort(personList);

        List<Person> expectedPersonList = new ArrayList<>();
        expectedPersonList.add(new Person("Artem", "Ekaterinburg", 31));
        expectedPersonList.add(new Person("Artem", "Ekaterinburg", 30));
        expectedPersonList.add(new Person("Stas", "Moscow", 25));
        expectedPersonList.add(new Person("Yulia", "Moscow", 35));
        expectedPersonList.add(new Person("Katya", "Novosibirsk", 30));
        expectedPersonList.add(new Person("Kostya", "Novosibirsk", 32));
        expectedPersonList.add(new Person("Misha", "Omsk", 27));
        expectedPersonList.add(new Person("Sasha", "Omsk", 35));
        Assertions.assertIterableEquals(expectedPersonList, personList);
    }

    @Test
    public void whenSortedPersonListAndOneObjectIsNull() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Misha", "Omsk", 27));
        personList.add(new Person("Sasha", "Omsk", 35));
        personList.add(null);
        personList.add(new Person("Artem", "Ekaterinburg", 31));
        personList.add(new Person("Artem", "Ekaterinburg", 30));
        personList.add(new Person("Yulia", "Moscow", 35));
        personList.add(new Person("Kostya", "Novosibirsk", 32));
        personList.add(new Person("Katya", "Novosibirsk", 30));
        Assertions.assertThrows(NullPointerException.class, () -> {
            Collections.sort(personList);
        });
    }
}
