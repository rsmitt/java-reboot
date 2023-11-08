package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {

        if (o1 == null || o2 == null){
            throw new RuntimeException();
        }

        int result1 = o1.intValue() % 2;
        int result2 = o2.intValue() % 2;

        if (result1 == result2) {
            return 0;
        } else {
            if (result1 != 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
