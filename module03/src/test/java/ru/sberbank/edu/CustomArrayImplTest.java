package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.impl.CustomArrayImpl;

import java.util.ArrayList;
import java.util.Collection;

public class CustomArrayImplTest {

    @Test
    public void createConstructorNoArgsTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>();
        Assertions.assertEquals(0, stringCustomArray.size());
        Assertions.assertEquals(15, stringCustomArray.getCapacity());
    }

    @Test
    public void createConstructorWithArgsTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>(25);
        Assertions.assertEquals(0, stringCustomArray.size());
        Assertions.assertEquals(25, stringCustomArray.getCapacity());

        stringCustomArray = new CustomArrayImpl<>(0);
        Assertions.assertEquals(0, stringCustomArray.size());
        Assertions.assertEquals(0, stringCustomArray.getCapacity());
    }

    @Test
    public void createConstructorWithIncorrectArgsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CustomArrayImpl<String>(-1);
        });
    }

    @Test
    public void checkSizeTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(0);
        Assertions.assertEquals(1, integerCustomArray.size());
        integerCustomArray.add(0);
        Assertions.assertEquals(2, integerCustomArray.size());
        integerCustomArray.add(3);
        Assertions.assertEquals(3, integerCustomArray.size());
    }

    @Test
    public void checkIsEmptyTest() {
        CustomArrayImpl<Double> doubleCustomArray = new CustomArrayImpl<>();
        Assertions.assertTrue(doubleCustomArray.isEmpty());
        doubleCustomArray = new CustomArrayImpl<>(2);
        Assertions.assertTrue(doubleCustomArray.isEmpty());
        doubleCustomArray.add(10D);
        Assertions.assertFalse(doubleCustomArray.isEmpty());
    }

    @Test
    public void checkAddItemTest() {
        CustomArrayImpl<Long> longCustomArray = new CustomArrayImpl<>();
        longCustomArray.add(100L);
        longCustomArray.add(101L);
        longCustomArray.add(111L);
        Assertions.assertEquals(100L, longCustomArray.get(0));
        Assertions.assertEquals(101L, longCustomArray.get(1));
        Assertions.assertEquals(111L, longCustomArray.get(2));
    }

    @Test
    public void checkAddItemWithGrowTest() {
        CustomArrayImpl<Long> longCustomArray = new CustomArrayImpl<>(5);
        longCustomArray.add(100L);
        longCustomArray.add(101L);
        longCustomArray.add(102L);
        Assertions.assertEquals(5, longCustomArray.getCapacity());
        Assertions.assertEquals(3, longCustomArray.size());
        longCustomArray.add(103L);
        Assertions.assertEquals(7, longCustomArray.getCapacity());
        Assertions.assertEquals(4, longCustomArray.size());
    }

    @Test
    public void checkAddAllArrayTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>();
        String[] array = {"A", "B", "C"};
        stringCustomArray.addAll(array);
        Assertions.assertEquals(15, stringCustomArray.getCapacity());
        Assertions.assertEquals(3, stringCustomArray.size());
        Assertions.assertEquals("A", stringCustomArray.get(0));
        Assertions.assertEquals("B", stringCustomArray.get(1));
        Assertions.assertEquals("C", stringCustomArray.get(2));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringCustomArray.addAll((String[]) null);
        });
    }

    @Test
    public void checkAddAllCollectionTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>();
        stringCustomArray.add("A");
        stringCustomArray.add("B");
        stringCustomArray.add("C");
        Collection<String> arrayList = new ArrayList<>();
        arrayList.add("D");
        arrayList.add("E");
        stringCustomArray.addAll(arrayList);
        Assertions.assertEquals(15, stringCustomArray.getCapacity());
        Assertions.assertEquals(5, stringCustomArray.size());
        Assertions.assertEquals("A", stringCustomArray.get(0));
        Assertions.assertEquals("B", stringCustomArray.get(1));
        Assertions.assertEquals("C", stringCustomArray.get(2));
        Assertions.assertEquals("D", stringCustomArray.get(3));
        Assertions.assertEquals("E", stringCustomArray.get(4));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringCustomArray.addAll((Collection<String>) null);
        });
    }

    @Test
    public void checkAddAllByIndexMiddleInsertTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>();
        stringCustomArray.add("A");
        stringCustomArray.add("B");
        stringCustomArray.add("C");
        String[] array = {"D", "E"};
        stringCustomArray.addAll(1, array);
        Assertions.assertEquals(15, stringCustomArray.getCapacity());
        Assertions.assertEquals(5, stringCustomArray.size());
        Assertions.assertEquals("A", stringCustomArray.get(0));
        Assertions.assertEquals("D", stringCustomArray.get(1));
        Assertions.assertEquals("E", stringCustomArray.get(2));
        Assertions.assertEquals("B", stringCustomArray.get(3));
        Assertions.assertEquals("C", stringCustomArray.get(4));

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stringCustomArray.addAll(5, array);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stringCustomArray.addAll((String[]) null);
        });
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stringCustomArray.addAll(-1, new String[]{"A", "B"});
        });
    }

    @Test
    public void checkAddAllByIndexStartInsertTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>();
        stringCustomArray.add("A");
        stringCustomArray.add("B");
        stringCustomArray.add("C");
        String[] array = {"D", "E"};
        stringCustomArray.addAll(0, array);
        Assertions.assertEquals(15, stringCustomArray.getCapacity());
        Assertions.assertEquals(5, stringCustomArray.size());
        Assertions.assertEquals("D", stringCustomArray.get(0));
        Assertions.assertEquals("E", stringCustomArray.get(1));
        Assertions.assertEquals("A", stringCustomArray.get(2));
        Assertions.assertEquals("B", stringCustomArray.get(3));
        Assertions.assertEquals("C", stringCustomArray.get(4));
    }

    @Test
    public void checkAddAllByIndexEndInsertTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>();
        stringCustomArray.add("A");
        stringCustomArray.add("B");
        stringCustomArray.add("C");
        String[] array = {"D", "E"};
        stringCustomArray.addAll(2, array);
        Assertions.assertEquals(15, stringCustomArray.getCapacity());
        Assertions.assertEquals(5, stringCustomArray.size());
        Assertions.assertEquals("A", stringCustomArray.get(0));
        Assertions.assertEquals("B", stringCustomArray.get(1));
        Assertions.assertEquals("D", stringCustomArray.get(2));
        Assertions.assertEquals("E", stringCustomArray.get(3));
        Assertions.assertEquals("C", stringCustomArray.get(4));
    }

    @Test
    public void checkGetTest() {
        CustomArrayImpl<String> stringCustomArray = new CustomArrayImpl<>();
        stringCustomArray.add("A");
        stringCustomArray.add("B");
        Assertions.assertEquals("A", stringCustomArray.get(0));
        Assertions.assertEquals("B", stringCustomArray.get(1));

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            stringCustomArray.get(2);
        });
    }

    @Test
    public void checkSetTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        integerCustomArray.set(0, 4);
        Assertions.assertEquals(15, integerCustomArray.getCapacity());
        Assertions.assertEquals(3, integerCustomArray.size());
        Assertions.assertEquals(4, integerCustomArray.get(0));
        Assertions.assertEquals(2, integerCustomArray.get(1));
        Assertions.assertEquals(3, integerCustomArray.get(2));

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            integerCustomArray.set(13, 1);
        });
    }

    @Test
    public void checkRemoveByIdTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        Assertions.assertEquals(15, integerCustomArray.getCapacity());
        Assertions.assertEquals(3, integerCustomArray.size());

        integerCustomArray.remove(1);
        Assertions.assertEquals(15, integerCustomArray.getCapacity());
        Assertions.assertEquals(2, integerCustomArray.size());
        Assertions.assertEquals(1, integerCustomArray.get(0));
        Assertions.assertEquals(3, integerCustomArray.get(1));

    }

    @Test
    public void checkRemoveByItemTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        Assertions.assertEquals(15, integerCustomArray.getCapacity());
        Assertions.assertEquals(3, integerCustomArray.size());
        Integer index = 4;
        Assertions.assertFalse(integerCustomArray.remove(index));
        index = 3;
        Assertions.assertTrue(integerCustomArray.remove(index));
        Assertions.assertEquals(15, integerCustomArray.getCapacity());
        Assertions.assertEquals(2, integerCustomArray.size());
        Assertions.assertEquals(1, integerCustomArray.get(0));
        Assertions.assertEquals(2, integerCustomArray.get(1));
    }

    @Test
    public void checkContainsTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        Assertions.assertTrue(integerCustomArray.contains(1));
        Assertions.assertFalse(integerCustomArray.contains(4));
    }

    @Test
    public void checkIndexOfTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        Assertions.assertEquals(2, integerCustomArray.indexOf(3));
        Assertions.assertEquals(-1, integerCustomArray.indexOf(5));
    }

    @Test
    public void checkEnsureCapacityTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        Assertions.assertEquals(15, integerCustomArray.getCapacity());
        Assertions.assertEquals(3, integerCustomArray.size());
        integerCustomArray.ensureCapacity(25);
        Assertions.assertEquals(25, integerCustomArray.getCapacity());
        Assertions.assertEquals(3, integerCustomArray.size());
    }

    @Test
    public void checkGetCapacity() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        Assertions.assertEquals(15, integerCustomArray.getCapacity());
        integerCustomArray = new CustomArrayImpl<>(10);
        Assertions.assertEquals(10, integerCustomArray.getCapacity());
    }

    @Test
    public void checkReverseTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        integerCustomArray.add(4);
        integerCustomArray.add(5);
        integerCustomArray.reverse();
        Assertions.assertEquals(5, integerCustomArray.get(0));
        Assertions.assertEquals(4, integerCustomArray.get(1));
        Assertions.assertEquals(3, integerCustomArray.get(2));
        Assertions.assertEquals(2, integerCustomArray.get(3));
        Assertions.assertEquals(1, integerCustomArray.get(4));

        integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        integerCustomArray.add(4);
        integerCustomArray.reverse();
        Assertions.assertEquals(4, integerCustomArray.get(0));
        Assertions.assertEquals(3, integerCustomArray.get(1));
        Assertions.assertEquals(2, integerCustomArray.get(2));
        Assertions.assertEquals(1, integerCustomArray.get(3));
    }

    @Test
    public void checkToStringTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        Assertions.assertEquals("[ 1 2 3 ]", integerCustomArray.toString());

        integerCustomArray = new CustomArrayImpl<>();
        Assertions.assertEquals("[ ]", integerCustomArray.toString());
    }

    @Test
    public void checkToArrayTest() {
        CustomArrayImpl<Integer> integerCustomArray = new CustomArrayImpl<>();
        integerCustomArray.add(1);
        integerCustomArray.add(2);
        integerCustomArray.add(3);
        Object[] array = integerCustomArray.toArray();
        Assertions.assertEquals(integerCustomArray.get(0), array[0]);
        Assertions.assertEquals(integerCustomArray.get(1), array[1]);
        Assertions.assertEquals(integerCustomArray.get(2), array[2]);
    }
}
