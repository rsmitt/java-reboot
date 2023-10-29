package ru.sberbank.edu;

/**
 * Класс, реализующий интерфейс CommonDivisor
 * @author  Нофал Кайс
 * @version 1.0
 */
public class GCD implements CommonDivisor{

    /**
     * Переопределение метода getDivisor из интерфейса CommonDivisor
     * @param firstNumber - целое число
     * @param secondNumber - целое число
     * @return возвращает целое число
     */

    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        if (firstNumber == 0 && secondNumber == 0) {
            return 0;
        } else if (firstNumber == 0 || secondNumber == 0) {
            return Math.abs(firstNumber) + Math.abs(secondNumber);
        } else if (Math.abs(firstNumber) == Math.abs(secondNumber)) {
            return Math.abs(firstNumber);
        } else {
            firstNumber = Math.abs(firstNumber);
            secondNumber = Math.abs(secondNumber);
            while (firstNumber * secondNumber != 0) {
                if (firstNumber > secondNumber) {
                    firstNumber = firstNumber % secondNumber;
                } else {
                    secondNumber = secondNumber % firstNumber;
                }
            }
            return firstNumber + secondNumber;
        }
    }
}
