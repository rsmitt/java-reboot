package ru.sberbank.edu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Юнит тесты по ДЗ №4
 */
public class AppTest
{
    /**
     * Тест CustomDigitComparator
     */
    @Test
    public void testForCustomDigitComparatorSorting()
    {
        CustomDigitComparator intComparator = new CustomDigitComparator();

        /**
         * Задаем ArrayList и наполняем его
         */
        ArrayList<Integer> arrList = new ArrayList<>();
        arrList.add(-9);
        arrList.add(3);
        arrList.add(-1);
        arrList.add(4);
        arrList.add(6);
        arrList.add(-2);
        arrList.add(8);
        arrList.add(-4);
        arrList.add(-3);
        arrList.add(10);

        // arrayUnsorted = {-9, 3, -1, 4, 6, -2, 8, -4, -3, 10};

        /**
         * Сортируем его
         */
        arrList.sort(intComparator);

        /**
         * Счетчик количества четных чисел в массиве
         */
        int countEven = 0;

        /**
         * Переводим ArrayList в новый массив из 10 элементов
         */
        int[] arraySorted = new int[10];
        for (int i = 0; i < arrList.size(); i++) {
            arraySorted[i] = arrList.get(i);
            if (arraySorted[i] % 2 == 0) {
                countEven++;
            }
        }

        // arraySorted = {4, 6, -2, 8, -4, 10, -9, 3, -1, -3};

        /**
         * Проверяем правильность сортировки массива
         */
        assertEquals(0, arraySorted[0] % 2);
        assertEquals(0, arraySorted[countEven - 1] % 2);
        assertTrue(arraySorted[countEven] % 2 != 0);
        assertTrue(arraySorted[9] % 2 != 0);

    }

    /**
     * Тест Person
     */
    @Test
    public void testForPersonEquals()
    {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Johnny", "Rostov", 25));
        list.add(new Person("JOHNNY", "RosToV", 25));
        list.add(new Person("Ibrahim", "Moscow", 30));
        list.add(new Person("Vasiliy", "Piter", 36));
        list.add(new Person("Dzugashvilli", "Tbilisi", 38));
        list.add(new Person("Maga", "Mahachkala", 39));
        list.add(new Person("Petro", "Piter", 40));
        list.add(new Person("Anatoliy", "Moscow", 42));

        /**
         * Проверяем корректность работы нашего метода "equals"
         */
        assertTrue(list.get(0).equals(list.get(1)));
        assertFalse(list.get(0).equals(list.get(2)));

        System.out.println("Unsorted: " + list);

        list.sort(Person::compareTo);

        System.out.println("Sorted: " + list);

        /**
         * Проверяем корректность первой линии сортировки (по городу)
         */
        assertEquals(list.get(0), new Person("Maga", "Mahachkala", 39));
        assertEquals(list.get(7), new Person("Dzugashvilli", "Tbilisi", 38));


        /**
         * Проверяем корректность второй линии сортировки (по имени)
         */
        int i1 = list.indexOf(new Person("Ibrahim", "Moscow", 30));
        int i2 = list.indexOf(new Person("Anatoliy", "Moscow", 42));
        int i3 = list.indexOf(new Person("Vasiliy", "Piter", 36));
        int i4 = list.indexOf(new Person("Petro", "Piter", 40));
        assertEquals(i1 - i2, 1);
        assertEquals(i3 - i4, 1);

    }
}