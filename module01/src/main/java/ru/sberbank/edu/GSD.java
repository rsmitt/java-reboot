package ru.sberbank.edu;

public class GSD implements CommonDivisor{

    GSD() {
    }

    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        while (secondNumber != 0) {
            int tmp = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = tmp;
        }
        return firstNumber;
    }
}
