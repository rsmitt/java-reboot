package ru.sberbank.edu;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestCustomDigitComparator {

@Test
    public void testCompare(){

    List<Integer> list = new ArrayList<>();

    Comparator<Integer> comp = new CustomDigitComparator();

    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);

    List<Integer> sortList = new ArrayList<>(list);
    Assertions.assertEquals(list,sortList);

    sortList.sort(comp);
    Assertions.assertNotEquals(list,sortList);

    }
}
