package test;

import org.testng.annotations.Test;
import static org.junit.Assert.*;

import ru.sberbank.edu.CustomArrayImpl;

import java.util.ArrayList;


public class CustomArrayImplTest {
    @Test
    public void WhenAdd() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        int ExpectedResult_0 = 15;
        int ExpectedResult_1 = 16;
        assertEquals(ExpectedResult_0, array.get(0));
        assertEquals(ExpectedResult_1, array.get(1));
    }

    @Test
    public void WhenAddAllArray() {
        Integer[] val = new Integer[] {1,2,3,4,5,6,7,8};
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.addAll(val);
        Integer[] ExpectedResult = new Integer[]{15,16,1,2,3,4,5,6,7,8};
        assertArrayEquals(ExpectedResult, array.toArray());
    }

    @Test
    public void WhenAddAllArrayForIndex() {
        Integer[] val = new Integer[] {1,2,3,4,5,6,7,8};
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.addAll(1, val);
        Integer[] ExpectedResult = new Integer[]{15,1,2,3,4,5,6,7,8,16};
        assertArrayEquals(ExpectedResult, array.toArray());
        assertArrayEquals(ExpectedResult, array.toArray());
    }

    @Test
    public void WhenAddAllCollection() {
        ArrayList<Integer> val = new ArrayList<>();
        val.add(3);
        val.add(4);
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.addAll(val);
        Integer[] ExpectedResult = new Integer[]{15,16,3,4};
        assertArrayEquals(ExpectedResult, array.toArray());
    }

    @Test
    public void WhenSet() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        array.set(2, 20);
        int ExpectedResult = 20;
        assertEquals(ExpectedResult, array.get(2));
    }

    @Test
    public void WhenSize() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        int ExpectedResult = 4;
        assertEquals(ExpectedResult, array.size());
    }

    @Test
    public void WhenIsEmpty() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        boolean ExpectedResult = false;
        assertEquals(ExpectedResult, array.isEmpty());
    }

    @Test
    public void WhenIndexOf() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        int ExpectedResult = 2;
        assertEquals(ExpectedResult, array.indexOf(17));
    }

    @Test
    public void WhenContains() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        boolean ExpectedResult = true;
        assertEquals(ExpectedResult, array.contains(16));
    }

    @Test
    public void WhenGetCapacity() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        int ExpectedResult = 10;
        assertEquals(ExpectedResult, array.getCapacity());
    }

    @Test
    public void WhenEnsureCapacity() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        array.ensureCapacity(7);
        int ExpectedResult = 17;
        assertEquals(ExpectedResult, array.getCapacity());
    }

    @Test
    public void WhenRemoveForIndex() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        array.remove(1);
        boolean ExpectedResult_1 = false;
        int ExpectedResult_2 = 3;
        assertEquals(ExpectedResult_1, array.contains(16));
        assertEquals(ExpectedResult_2, array.size());
    }

    @Test
    public void WhenRemoveForValue() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        array.remove(new Integer(16));
        boolean ExpectedResult_1 = false;
        int ExpectedResult_2 = 3;
        assertEquals(ExpectedResult_1, array.contains(16));
        assertEquals(ExpectedResult_2, array.size());
    }

    @Test
    public void WhenToArray() {
        CustomArrayImpl<Integer> array = new CustomArrayImpl<>();
        array.add(15);
        array.add(16);
        array.add(17);
        array.add(18);
        array.reverse();
        Integer[] ExpectedResult = new Integer[]{18,17,16,15};
        assertArrayEquals(ExpectedResult, array.toArray());
    }


}
