package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer lhs, Integer rhs) {
        if (isEven(lhs) && !isEven(rhs)) {
            return -1;
        }
        if (!isEven(lhs) && isEven(rhs)) {
            return 1;
        } else return 0;
    }

    private boolean isEven(Integer i) {
        return i % 2 == 0;
    }
}