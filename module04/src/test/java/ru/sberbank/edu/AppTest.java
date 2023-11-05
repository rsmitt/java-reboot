package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    @DisplayName("Тест кастомного числового компаратора")
    public void testCustomDigitComparator() {
        CustomDigitComparator comparator = new CustomDigitComparator();
        Assertions.assertTrue(comparator.compare(7, 2) < 0 &&
                comparator.compare(1, 4) < 0 &&
                comparator.compare(2, 9) > 0 &&
                comparator.compare(6, 5) > 0 &&
                comparator.compare(7, 7) == 0 &&
                comparator.compare(4, 4) == 0);
    }

    @Test
    @DisplayName("Тест1 конструктора в классе Person")
    public void testPerson1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(null, "N", 30));
    }

    @Test
    @DisplayName("Тест2 конструктора в классе Person")
    public void testPerso2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("N", null, 30));
    }

    @Test
    @DisplayName("Тест метода equals в классе Person")
    public void testPersonEquals() {
        Person person1 = new Person("Kays", "Moscow", 30);
        Person person2 = new Person("kAYS", "mOSCOW", 30);
        Person person3 = new Person("Kays", "Moscow", 31);
        Person person4 = new Person("Kays", "Tver", 30);
        Person person5 = new Person("Kays", "Moscow", 30);
        Assertions.assertTrue(person1.equals(person2) &&
                person1.equals(person5) &&
                !person1.equals(person3) &&
                !person1.equals(person4));
    }

    @Test
    @DisplayName("Тест метода compareTo в классе Person")
    public void testCompareTo() {
        Person person1 = new Person("1", "2", 30);
        Person person2 = new Person("1", "11", 30);
        Person person3 = new Person("1", "2", 33);
        Person person4= new Person("1", "2", 29);
        Person person5 = new Person("1", "2", 30);
        Person person6 = new Person("2", "1", 30);
        Person person7 = new Person("11", "1", 30);
        Assertions.assertTrue(person1.compareTo(person2) > 0 &&
                person1.compareTo(person3) < 0 &&
                person1.compareTo(person4) > 0 &&
                person1.compareTo(person5) == 0 &&
                person6.compareTo(person7) > 0);
    }
}
