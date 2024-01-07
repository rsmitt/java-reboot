package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayImplTest {


    @Test
    void addAllArray() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        Integer[] ints = {1,2,3,4};
        boolean addStatus = array.addAll(ints);
        assertTrue(addStatus);
        assertEquals(4, array.size());
    }

    @Test
    void addAllCollection() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        Collection<Integer> ints = Arrays.asList(1, 2, 3, 4);
        boolean addStatus = array.addAll(ints);
        assertTrue(addStatus);
        assertEquals(4, array.size());
    }

    @Test
    void addAllAtIndex() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        Integer[] newInts = {3, 4};
        assertTrue(array.addAll(1, newInts));
        assertEquals(4, array.size());
        assertEquals(3, array.get(1));
    }




    @Test
    void removeByIndex() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(1);
        assertFalse(array.contains(2));
    }

    @Test
    void removeByItem() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(Integer.valueOf(2));
        assertFalse(array.contains(2));
    }

    @Test
    void contains() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertTrue(array.contains(2));
    }

    @Test
    void indexOf() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(5);
        assertEquals(1, array.indexOf(2));
    }

    @Test
    void reverse() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(5);
        array.reverse();
        assertEquals(2, array.indexOf(1));
    }
    @Test
    void add() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(5);
        assertTrue(array.contains(5));
    }

    @Test
    void get() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertEquals(2, array.get(1));
    }

    @Test
    void size() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        array.add(3);
        assertEquals(3, array.size());
    }

    @Test
    void isEmpty() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        assertTrue(array.isEmpty());
        array.add(1);
        assertFalse(array.isEmpty());
    }

    @Test
    void set() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        assertEquals(1, array.set(0, 2));
        assertEquals(2, array.get(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(5, 3));
    }

    @Test
    void removeAt() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        assertDoesNotThrow(() -> array.remove(0));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(5));
    }

    @Test
    void remove() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(1);
        array.add(2);
        assertTrue(array.remove(new Integer(1)));
        assertFalse(array.remove(new Integer(5)));
    }
    @Test
    void ensureCapacity() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();

        for(int i=0; i<10; i++){
            array.add(i);
        }

        array.ensureCapacity(20);
        assertEquals(20, array.getCapacity());
    }

    @Test
    void getCapacity() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();

        for(int i=0; i<10; i++){
            array.add(i);
        }

        assertEquals(10, array.getCapacity());
    }
    @Test
    void toStringTest() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        for(int i=0; i<3; i++){
            array.add(i);
        }

        String expected = "[0, 1, 2]";
        assertEquals(expected, array.toString());
    }

    @Test
    void toArray() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        for(int i=0; i<3; i++){
            array.add(i);
        }

        Object[] expected = new Object[] {0, 1, 2};
        assertArrayEquals(expected, array.toArray());
    }

}