package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonTests {

    @Test
    public void PersonCompareToTest() {

        List<Person> myList = new ArrayList<>();
        myList.add(new Person("Ivan", "Moscow", 20));
        myList.add(new Person("Alexey", "Moscow", 33));
        myList.add(new Person("Ivan", "Dmitrov", 56));
        myList.add(new Person("Roman", "Kolomna", 11));
        myList.add(new Person("Olga", "moscow", 54));
        myList.add(new Person("Elena", "Moscow", 74));

        myList.sort(Person::compareTo);

        Assertions.assertEquals(myList.toString(), "[Person{name='Ivan', city='Dmitrov', age=56}, Person{name='Roman', city='Kolomna', age=11}, Person{name='Alexey', city='Moscow', age=33}, Person{name='Elena', city='Moscow', age=74}, Person{name='Ivan', city='Moscow', age=20}, Person{name='Olga', city='moscow', age=54}]");

    }

    @Test
    public void PersonEqualsTest() {

        Person p1 = new Person("Ivan", "Moscow", 20);
        Person p2 = new Person("Alexey", "Moscow", 33);
        Person p3 = new Person("ivaN", "moscoW", 20);
        Person p4 = new Person("Ivan", "Moscow", 21);

        Assertions.assertFalse(p1.equals(p2));
        Assertions.assertTrue(p1.equals(p3));
        Assertions.assertFalse(p1.equals(p4));

    }

    @Test
    public void PersonHashCodeTest() {

        Person p1 = new Person("Ivan", "Moscow", 20);
        Person p2 = new Person("Alexey", "Moscow", 33);
        Person p3 = new Person("ivaN", "moscoW", 20);
        Person p4 = new Person("Ivan", "Moscow", 21);

        Assertions.assertNotEquals(p1.hashCode(), p2.hashCode());
        Assertions.assertEquals(p1.hashCode(), p3.hashCode());
        Assertions.assertNotEquals(p1.hashCode(), p4.hashCode());

    }

}
