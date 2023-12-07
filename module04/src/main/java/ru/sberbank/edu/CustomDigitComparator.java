package ru.sberbank.edu;

import java.util.Comparator;

import static java.util.Objects.isNull;

public class CustomDigitComparator implements Comparator<Integer> {
    /**
     * Метод compare класса определяет следующий порядок:
     * - Сначала четные числа, затем нечетные
     * - На вход подаются числа, отличные от null.
     */
    @Override
    public int compare(Integer o1, Integer o2) {

        if (isNull(o1) || isNull(o2)) {
            throw new IllegalArgumentException("Arguments should be not equals null");
        } else if (o1 % 2 == 0 && o2 % 2 == 1) {
            return -1;
        } else if (o1 % 2 == 1 && o2 % 2 == 0) {
            return 1;
        } else return 0;

    }
}
