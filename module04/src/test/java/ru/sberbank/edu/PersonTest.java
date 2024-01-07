package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testCompareTo_SameCityAndSameName() {
        Person person1 = new Person("John", "New York", 30);
        Person person2 = new Person("John", "New York", 25);

        int result = person1.compareTo(person2);

        Assertions.assertEquals(0, result);
    }

    @Test
    void testCompareTo_SameCityAndDifferentName() {
        Person person1 = new Person("Alice", "New York", 30);
        Person person2 = new Person("Bob", "New York", 25);

        int result = person1.compareTo(person2);

        assertTrue(result < 0);
    }

    @Test
    void testCompareTo_DifferentCity() {
        Person person1 = new Person("John", "Los Angeles", 30);
        Person person2 = new Person("Bob", "New York", 25);

        int result = person1.compareTo(person2);

        assertTrue(result < 0);
    }

    @Test
    void testEquals_SamePerson() {
        Person person1 = new Person("John", "New York", 30);
        Person person2 = new Person("John", "New York", 30);

       assertTrue(person2.equals(person1));
    }

    @Test
    void testEquals_DifferentPerson() {
        Person person1 = new Person("John", "New York", 30);
        Person person2 = new Person("Alice", "Los Angeles", 25);

        assertFalse(person2.equals(person1));
    }

    @Test
    void checkHashCodeTest(){
        Person person1 = new Person("John", "New York", 30);
        Person person2 = new Person("John", "New York", 30);
        int person1HashCode = person1.hashCode();
        int person2HashCode = person2.hashCode();
        assertEquals(person1HashCode,person2HashCode);
    }
    @Test
    void checkToStringTest(){
        Person person1 = new Person("John", "New York", 30);
        String result = person1.toString();
        assertEquals("Person{name='John', city='New York', age=30}",result);
    }
}