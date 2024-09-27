package ru.sberbank.edu;

/**
 * @author      Чернов Алексей
 */
public class MyCommonDivisor implements CommonDivisor {
    /**
     * Метод расчета НОД по алгоритму Евклида
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {

        while (firstNumber > 0 && secondNumber > 0) {
            if ((secondNumber % firstNumber) == 0) {
                return firstNumber;
            }

            secondNumber %= firstNumber;

            if ((firstNumber % secondNumber) == 0) {
                return secondNumber;
            }

            firstNumber %= secondNumber;
        }

        if (firstNumber == 0) {
            return secondNumber;
        }

        return firstNumber;
    }
}
