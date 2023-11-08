package ru.sberbank.edu;

import org.testng.annotations.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AppTest {
    @Test
    public void WhenCompareToByCityAndName() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Makar", "Moscow", 1));
        persons.add(new Person("Ivan", "Omsk", 55));
        persons.add(new Person("Artyom", "Omsk", 20));
        persons.add(new Person("Boris", "Moscow", 29));
        persons.sort(Person::compareTo);
        List<Person> expectedListPersons = new ArrayList<>();
        expectedListPersons.add(new Person("Boris", "Moscow", 29));
        expectedListPersons.add(new Person("Makar", "Moscow", 1));
        expectedListPersons.add(new Person("Artyom", "Omsk", 20));
        expectedListPersons.add(new Person("Ivan", "Omsk", 55));
        assertArrayEquals(persons.toArray(), expectedListPersons.toArray());
    }

    @Test
    public void WhenCustomDigitComparator() {
        Integer[] ints = new Integer[]{0, 4, 2, 15, 24, 0, 42, 63, 16, 9};
        List<Integer> digits = new ArrayList(List.of(ints));
        Comparator<Integer> cmp = new CustomDigitComparator();
        digits.sort(cmp);
        Integer[] result = new Integer[]{0, 4, 2, 24, 0, 42, 16, 9, 63, 15};
        assertArrayEquals(digits.toArray(), result);
    }
}
