package ru.sberbank.edu;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        CustomArray<Integer> customArray = new CustomArrayImpl<>();
        customArray.add(1);
        customArray.add(2);
        customArray.add(3);
        customArray.addAll(new Integer[]{4, 5, 6, 7, 8, 9});
        customArray.addAll(5, new Integer[]{10, 11, 12, 13, 14});
        System.out.println(customArray);
        customArray.reverse();
        System.out.println(customArray);
        customArray.set(14, 1);
        System.out.println(customArray);
        System.out.println(customArray.size());
        System.out.println(customArray.getCapacity());
        customArray.remove(7);
        System.out.println(customArray);
        System.out.println(customArray.size());
        System.out.println(customArray.getCapacity());
        customArray.remove((Integer) 13);
        System.out.println(customArray);
        System.out.println(customArray.size());
        System.out.println(customArray.getCapacity());
        customArray.ensureCapacity(40);
        System.out.println(customArray);
        System.out.println(customArray.size());
        System.out.println(customArray.getCapacity());
    }
}
