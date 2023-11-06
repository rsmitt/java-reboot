package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomDigitComparatorTest {

    @Test
    public void whenOneOfNumberIsNull() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(7);
        list.add(null);
        list.add(5);
        list.add(4);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            list.sort(new CustomDigitComparator());
        });
    }

    @Test
    public void whenFirstNumberIsEven() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.sort(new CustomDigitComparator());
        Assertions.assertEquals(Arrays.asList(2, 5), list);
    }

    @Test
    public void whenSecondNumberIsEven() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(6);
        list.sort(new CustomDigitComparator());
        Assertions.assertEquals(Arrays.asList(6, 3), list);
    }

    @Test
    public void whenBothdNumbersIsEven() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(6);
        list.sort(new CustomDigitComparator());
        Assertions.assertEquals(Arrays.asList(2, 6), list);
    }

    @Test
    public void whenBothdNumbersIsEvenAndEquals() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.sort(new CustomDigitComparator());
        Assertions.assertEquals(Arrays.asList(2, 2), list);
    }

    @Test
    public void whenBothdNumbersIsNotEvenAndEquals() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(3);
        list.sort(new CustomDigitComparator());
        Assertions.assertEquals(Arrays.asList(3, 3), list);
    }

    @Test
    public void whenBothdNumbersIsNotEven() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.sort(new CustomDigitComparator());
        Assertions.assertEquals(Arrays.asList(3, 5), list);
    }

    @Test
    public void whenCommonSortArray() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(2);
        list.add(4);
        list.add(10);
        list.add(1);
        list.add(7);
        list.add(9);
        list.add(8);
        list.add(3);
        list.sort(new CustomDigitComparator());
        Assertions.assertEquals(Arrays.asList(2, 4, 6, 8, 10, 1, 3, 5, 7, 9), list);
    }
}
