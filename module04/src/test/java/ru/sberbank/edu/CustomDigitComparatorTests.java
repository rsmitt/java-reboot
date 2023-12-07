package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomDigitComparatorTests {

    @Test
    public void customDigitComparatorTestWithNull() {

        List<Integer> myList = new ArrayList<>();
        myList.add(4);
        myList.add(7);
        myList.add(2);
        myList.add(1);
        myList.add(null);

        Comparator<Integer> cmpInt = new CustomDigitComparator();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            myList.sort(cmpInt);
        });

        String expectedMessage = "Arguments should be not equals null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void customDigitComparatorTest() {

        List<Integer> myList = new ArrayList<>();
        myList.add(4);
        myList.add(7);
        myList.add(2);
        myList.add(1);

        Comparator<Integer> cmpInt = new CustomDigitComparator();

        myList.sort(cmpInt);

        List<Integer> myListAssert = new ArrayList<>();
        myListAssert.add(4);
        myListAssert.add(2);
        myListAssert.add(7);
        myListAssert.add(1);

        assertEquals(myList, myListAssert);

    }

    @Test
    public void customDigitComparatorTestEmpty() {

        List<Integer> myList = new ArrayList<>();

        Comparator<Integer> cmpInt = new CustomDigitComparator();

        myList.sort(cmpInt);

        Assertions.assertEquals(myList, new ArrayList<>());
    }

}
