package ru.sberbank.edu;

public class GCD implements CommonDivisor{
    /**
     * Метод выводит наибольший общий делитель 2х чисел. (Алгоримт Евклида)
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        int simpleCasesValue = simpleCases(firstNumber, secondNumber);
        if (simpleCasesValue != -1) {
            return simpleCasesValue;
        }
        int firstNum = firstNumber;
        int secondNum = secondNumber;
        while (firstNum != 0 && secondNum != 0) {
            if (firstNum > secondNum) {
                firstNum = firstNum - secondNum;
            }
            else {
                secondNum = secondNum - firstNum;
            }
        }
        return firstNum + secondNum;
    }


    /**
     * Метод выводит наибольший общий делитель 2х чисел для простого случая.
     */
    private int simpleCases(int firstNumber, int secondNumber) {
        if (firstNumber == secondNumber) {
            return firstNumber;
        }
        if (firstNumber == 0) {
            return secondNumber;
        }
        if (secondNumber == 0) {
            return firstNumber;
        }
        return -1;
    }
}

