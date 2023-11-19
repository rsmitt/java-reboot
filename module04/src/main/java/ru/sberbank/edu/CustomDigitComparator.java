package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {

//    /**
//     * Сортировка по четности, сначала четные числа, затем нечетные
//     * @param int1 первое число
//     * @param int2 второе число
//     * @return 0 - если числа равны, -1 - если первое число четное, 1 - во всех остальных случаях
//     */
//    @Override
//    public int compare(Integer int1, Integer int2) {
//        if (int1 == null || int2 == null) {
//            throw new IllegalArgumentException();
//        } else if (int1.equals(int2)) {
//            return 0;
//        } else if (int1 % 2 == 0) {
//            return -1;
//        } else {
//            return 1;
//        }
//    }

    @Override
    public int compare(Integer int1, Integer int2) {
        if (int1 == null || int2 == null) {
            throw new IllegalArgumentException();
        }
        if (int1.equals(int2)) {
            return 0;
        } else if (int1 % 2 == 0 && int2 % 2 == 0) {
            if (int1 < int2) {
                return -1;
            } else {
                return 1;
            }
        } else if (int1 % 2 != 0 && int2 % 2 != 0) {
            if (int1 < int2) {
                return -1;
            } else {
                return 1;
            }
        } else if (int1 % 2 == 0) {
            return -1;
        } else {
            return 1;
        }
    }
}