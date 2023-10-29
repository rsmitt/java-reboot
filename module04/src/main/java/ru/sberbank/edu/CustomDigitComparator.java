package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("метод компаратора не может содержать null-аргументы");
        } else {
            if (o1 % 2 == 0 && o2 % 2 != 0) {
                return 1;
            } else if (o1 % 2 != 0 && o2 % 2 == 0) {
                return -1;
            } else {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
