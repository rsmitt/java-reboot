package ru.sberbank.edu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomArrayImplTest {
    CustomArrayImpl<Integer> customArrayImplWithIntegers;

    @Before
    public void init() {
        customArrayImplWithIntegers = new CustomArrayImpl<>();
    }

    @After
    public void tearDown() {
        customArrayImplWithIntegers = null;
    }

    @Test
    public void CustomArrayImplWithIntegersSizeTest() {
        customArrayImplWithIntegers.add(10);
        customArrayImplWithIntegers.add(34);
        Assert.assertEquals("Size check: ", 2, 2);
    }

    @Test
    public void CustomArrayImplWithIntegersAddTest() {
        boolean result = customArrayImplWithIntegers.add(10);
        Assert.assertTrue("Element added", result);
    }

    @Test
    public void CustomEmptyArrayImplWithIntegersIsEmptyTest() {
        Assert.assertEquals(0, customArrayImplWithIntegers.size());
        Assert.assertTrue("Is empty", customArrayImplWithIntegers.isEmpty());
    }

    @Test
    public void CustomNotEmptyArrayImplWithIntegersIsEmptyTest() {
        customArrayImplWithIntegers.add(10);
        Assert.assertEquals(1, customArrayImplWithIntegers.size());
        Assert.assertFalse("Is empty", customArrayImplWithIntegers.isEmpty());
    }

    @Test
    public void CustomArrayImplWithIntegersAddAllTest() {
        Integer[] addedArray = new Integer[]{5, 10, 12};
        customArrayImplWithIntegers.addAll(addedArray);
        Assert.assertEquals(3, customArrayImplWithIntegers.size());
        Assert.assertEquals(Integer.valueOf(12), customArrayImplWithIntegers.get(2));
    }

    @Test
    public void CustomArrayImplWithIntegersRemoveByIndexTest() {
        customArrayImplWithIntegers.add(1);
        customArrayImplWithIntegers.add(2);
        customArrayImplWithIntegers.remove(1);
        Assert.assertEquals(1, customArrayImplWithIntegers.size());
    }

    @Test
    public void CustomArrayImplWithIntegersRemoveByValueTest() {
        customArrayImplWithIntegers.add(1);
        customArrayImplWithIntegers.add(2);
        Assert.assertTrue(customArrayImplWithIntegers.remove(Integer.valueOf(1)));
        Assert.assertFalse(customArrayImplWithIntegers.remove(Integer.valueOf(10)));
        Assert.assertEquals(1, customArrayImplWithIntegers.size());
    }

    @Test
    public void CustomArrayImplWithIntegersContainsTest() {
        customArrayImplWithIntegers.add(1);
        customArrayImplWithIntegers.add(2);
        Assert.assertTrue(customArrayImplWithIntegers.contains(1));
        Assert.assertFalse(customArrayImplWithIntegers.contains(10));
        Assert.assertEquals(2, customArrayImplWithIntegers.size());
    }

    @Test
    public void CustomArrayImplWithIntegersIndexOfTest() {
        customArrayImplWithIntegers.add(1);
        customArrayImplWithIntegers.add(2);
        customArrayImplWithIntegers.add(10);

        Assert.assertEquals(2, customArrayImplWithIntegers.indexOf(10));
        Assert.assertEquals(-1, customArrayImplWithIntegers.indexOf(20));
    }

    @Test
    public void CustomArrayImplWithIntegersReverseTest() {
        customArrayImplWithIntegers.add(1);
        customArrayImplWithIntegers.add(2);
        customArrayImplWithIntegers.add(10);

        Assert.assertEquals(Integer.valueOf(1), customArrayImplWithIntegers.get(0));
        Assert.assertEquals(Integer.valueOf(10), customArrayImplWithIntegers.get(2));

        customArrayImplWithIntegers.reverse();

        Assert.assertEquals(Integer.valueOf(10), customArrayImplWithIntegers.get(0));
        Assert.assertEquals(Integer.valueOf(1), customArrayImplWithIntegers.get(2));
    }

}
