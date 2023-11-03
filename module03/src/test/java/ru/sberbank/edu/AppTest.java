package ru.sberbank.edu;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Выборочные юнит-тесты на класс CustomArrayImpl
 */
public class AppTest
{




    /**
     * Проверка правильности получения размера ArrayList
     */
    @Test
    public void checkSize()
    {
        Object[] a = {7, 8};
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        cust.add("fff");
        cust.add(1);
        cust.addAll(1, a);
        assertEquals(cust.size(), 4);
    }


    /**
     * Проверка ArrayList на пустой/полный
     */
    @Test
    public void checkIsEmpty()
    {
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        cust.add("fff");
        cust.remove(0);
        assertTrue(cust.isEmpty());
        cust.add(1);
        assertFalse(cust.isEmpty());
    }




    /**
     * Проверка успешности добавления элементов разных типов в ArrayList
     */
    @Test
    public void checkAdd()
    {
        Object[] a = {7, 8};
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        assertTrue(cust.add("fff"));
        assertTrue(cust.add(3));
        assertTrue(cust.addAll(a));
        assertTrue(cust.addAll(3, a));
    }




    /**
     * Проверка функций Get и Set
     */
    @Test
    public void checkGetAndSet()
    {
        Object[] a = {7, 8};
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        cust.add("fff");
        cust.add(3);
        cust.addAll(a);
        cust.set(1, "bbb");
        assertEquals(cust.get(0), "fff");
        assertEquals(cust.get(1), "bbb");
        assertEquals(cust.get(2), 7);
        assertEquals(cust.get(3), 8);
    }




    /**
     * Проверка функции удаления из ArrayList
     */
    @Test
    public void checkRemove()
    {
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        cust.add("fff");
        cust.add(3);
        assertTrue(cust.remove("fff"));
        assertFalse(cust.remove("ddd"));
    }




    /**
     * Проверка, что ArrayList содержит конкретный элемент
     */
    @Test
    public void checkContains()
    {
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        cust.add("fff");
        cust.add(3);
        assertTrue(cust.contains("fff"));
        assertTrue(cust.contains(3));
        assertFalse(cust.contains("abc"));
    }




    /**
     * Проверка корректности функции разворота ArrayList
     */
    @Test
    public void checkReverse()
    {
        Object[] a = {7, 8};
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        cust.add("fff");
        cust.add('g');
        cust.addAll(a);
        cust.reverse();
        assertEquals(cust.get(0), 8);
        assertEquals(cust.get(1), 7);
        assertEquals(cust.get(2), 'g');
        assertEquals(cust.get(3), "fff");
    }



    /**
     * Проверка клонирования ArrayList в Array
     */
    @Test
    public void checkClone()
    {
        Object[] a = {7, 8};
        CustomArrayImpl<Object> cust = new CustomArrayImpl<>();
        cust.add("fff");
        cust.add('g');
        cust.addAll(a);
        Object[] new_arr = cust.toArray();
        assertEquals(cust.get(0), new_arr[0]);
        assertEquals(cust.get(1), new_arr[1]);
        assertEquals(cust.get(2), new_arr[2]);
        assertEquals(cust.get(3), new_arr[3]);
    }
}