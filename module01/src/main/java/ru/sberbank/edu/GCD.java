package ru.sberbank.edu;

/**
 * The Euclid's algorithm
 * @author Aleksandr Kanarskiy
 */
public class GCD implements CommonDivisor{
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        while (secondNumber !=0) {
            int tmp = firstNumber%secondNumber;
            firstNumber = secondNumber;
            secondNumber = tmp;
        }
        return firstNumber;
    }
}

