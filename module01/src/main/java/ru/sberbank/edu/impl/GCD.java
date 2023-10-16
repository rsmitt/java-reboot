package ru.sberbank.edu.impl;

import ru.sberbank.edu.CommonDivisor;

public class GCD implements CommonDivisor {

    /***
     * Метод, определяющий наибольший общий делитель двух целочисленных значений
     * @param firstNumber первое целое число
     * @param secondNumber второе целое число
     * @return наибольший общий делитель
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        firstNumber = Math.abs(firstNumber);
        secondNumber = Math.abs(secondNumber);
        if (secondNumber == 0) {
            return firstNumber;
        } else {
            return getDivisor(secondNumber, firstNumber % secondNumber);
        }
    }
}
