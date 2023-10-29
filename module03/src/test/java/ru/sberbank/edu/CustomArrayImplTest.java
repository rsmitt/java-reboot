package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayImplTest {

    @Test
    @DisplayName("Test for size isEmpty method")
    public void test_isEmpty() {
        CustomArray<Integer> customArray1 = new CustomArrayImpl<>();
        CustomArray<Integer> customArray2 = new CustomArrayImpl<>();
        customArray1.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Assertions.assertTrue(customArray2.isEmpty() && !customArray1.isEmpty());
    }

    @Test
    @DisplayName("Test for size method")
    public void test_size() {
        CustomArray<Integer> customArray1 = new CustomArrayImpl<>();
        CustomArray<Integer> customArray2 = new CustomArrayImpl<>();
        customArray1.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Assertions.assertTrue(customArray2.size() == 0 && customArray1.size() == 5);
    }

    @Test
    @DisplayName("Test for add method")
    public void test_add() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.add(1);
        Assertions.assertTrue(customArray.size() == 1 && customArray.get(0) == 1);
    }

    @Test
    @DisplayName("Test for addAll method")
    public void test_addAll() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Assertions.assertTrue(customArray.size() == 5 &&
                customArray.get(0) == 1 &&
                customArray.get(1) == 2 &&
                customArray.get(2) == 3 &&
                customArray.get(3) == 4 &&
                customArray.get(4) == 5
        );
    }

    @Test
    @DisplayName("Test for addAll with Collections method")
    public void test_addAllCollection() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        customArray.addAll(list);
        Assertions.assertTrue(customArray.size() == 5 &&
                customArray.get(0) == 1 &&
                customArray.get(1) == 2 &&
                customArray.get(2) == 3 &&
                customArray.get(3) == 4 &&
                customArray.get(4) == 5
        );
    }

    @Test
    @DisplayName("Test for addAll with index method")
    public void test_addAllIndex() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        customArray.addAll(3, new Integer[]{6, 7, 8});
        Assertions.assertTrue(customArray.size() == 8 &&
                customArray.get(0) == 1 &&
                customArray.get(1) == 2 &&
                customArray.get(2) == 3 &&
                customArray.get(3) == 6 &&
                customArray.get(4) == 7 &&
                customArray.get(5) == 8 &&
                customArray.get(6) == 4 &&
                customArray.get(7) == 5
        );
    }

    @Test
    @DisplayName("Test for get method")
    public void test_get() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Assertions.assertTrue(customArray.get(0) == 1 &&
                customArray.get(0) == 1 &&
                customArray.get(1) == 2 &&
                customArray.get(2) == 3 &&
                customArray.get(3) == 4 &&
                customArray.get(4) == 5
                );
    }

    @Test
    @DisplayName("Test for set method")
    public void test_set() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        customArray.set(4, 10);
        Assertions.assertTrue(customArray.size() == 6 &&
                customArray.get(0) == 1 &&
                customArray.get(1) == 2 &&
                customArray.get(2) == 3 &&
                customArray.get(3) == 4 &&
                customArray.get(4) == 10 &&
                customArray.get(5) == 5
        );
    }

    @Test
    @DisplayName("Test for remove method")
    public void test_remove() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        customArray.remove(3);
        Assertions.assertTrue(customArray.size() == 4 &&
                customArray.get(0) == 1 &&
                customArray.get(1) == 2 &&
                customArray.get(2) == 3 &&
                customArray.get(3) == 5
        );
    }

    @Test
    @DisplayName("Test for remove with item method")
    public void test_removeItem() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        customArray.remove((Integer) 4);
        Assertions.assertTrue(customArray.size() == 4 &&
                customArray.get(0) == 1 &&
                customArray.get(1) == 2 &&
                customArray.get(2) == 3 &&
                customArray.get(3) == 5
        );
    }

    @Test
    @DisplayName("Test for contains method")
    public void test_contains() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Assertions.assertTrue(customArray.contains((2)));
    }

    @Test
    @DisplayName("Test for indexOf method")
    public void test_indexOf() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Assertions.assertEquals(2, customArray.indexOf(3));
    }

    @Test
    @DisplayName("Test for ensureCapacity method")
    public void test_ensureCapacity() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.ensureCapacity(20);
        Assertions.assertEquals(20, customArray.getCapacity());
    }

    @Test
    @DisplayName("Test for getCapacity method")
    public void test_getCapacity() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        Assertions.assertEquals(10, customArray.getCapacity());
    }

    @Test
    @DisplayName("Test for reverse method")
    public void test_reverse() {
        CustomArray<Integer> customArray1 = new CustomArrayImpl<>();
        customArray1.addAll(new Integer[]{1, 2, 3, 4 ,5});
        CustomArray<Integer> customArray2 = new CustomArrayImpl<>();
        customArray2.addAll(new Integer[]{5, 4, 3, 2, 1});
        customArray1.reverse();
        Assertions.assertTrue(customArray1.size() == customArray2.size() &&
                customArray1.get(0).equals(customArray2.get(0)) &&
                customArray1.get(1).equals(customArray2.get(1)) &&
                customArray1.get(2).equals(customArray2.get(2)) &&
                customArray1.get(3).equals(customArray2.get(3)) &&
                customArray1.get(4).equals(customArray2.get(4))
        );
    }

    @Test
    @DisplayName("Test for toString method")
    public void test_toString() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Assertions.assertEquals("[1, 2, 3, 4, 5]", customArray.toString());
    }

    @Test
    @DisplayName("Test for toArray method")
    public void test_toArray() {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.addAll(new Integer[]{1, 2, 3, 4 ,5});
        Object[] ints = customArray.toArray();
        Integer[] ints1 = new Integer[]{1, 2, 3, 4 ,5};
        int check = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i].equals(ints1[i])) {
                check += 1;
            }
        }
        Assertions.assertEquals(ints.length, check);
    }
}
