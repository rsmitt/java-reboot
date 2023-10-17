package ru.sberbank.edu;

/***
 * НОД
 */
public class GCD implements CommonDivisor {

    /***
     * Возвращает наибольший общий делитель двух чисел
     * @param firstNumber - первое число
     * @param secondNumber - второе число
     * @return  наибольший общий делитель
     */

    public int getDivisor(int firstNumber, int secondNumber) {
        if (secondNumber > firstNumber) {
            int buf = firstNumber;
            firstNumber = secondNumber;
            secondNumber = buf;
        }

        int remainder;
        while (true) {
            remainder = firstNumber % secondNumber;
            if ( remainder == 0 ) {
                break;
            }
            firstNumber = secondNumber;
            secondNumber = remainder;
        }
        return secondNumber;
    }
}