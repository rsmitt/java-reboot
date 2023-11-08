package ru.sberbank.edu;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{


    @Test
    void main(){


        List<Integer> intList = new ArrayList<>();

        intList.add(1);
        intList.add(2);
        intList.add(2);
        intList.add(1);

        Comparator<Integer> comporator = new CustomDigitComparator();
        intList.sort(comporator);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Имя1","Город",15));
        personList.add(new Person("иМя","Город123",15));
        personList.add(new Person("имЯ","Город",15));
        personList.add(new Person("имя","Город",15));

        personList.sort(Person::compareTo);

        Assertions.assertThat(intList.get(0) == 2 && intList.get(1) == 2 && intList.get(2) == 1 && intList.get(3) == 1 ).isTrue();
        Assertions.assertThat(personList.get(0).getCity().equals("Город") && personList.get(0).getName().equals("имЯ") && personList.get(3).getCity().equals("Город123") && personList.get(3).getName().equals("иМя")  ).isTrue();
    }
}
