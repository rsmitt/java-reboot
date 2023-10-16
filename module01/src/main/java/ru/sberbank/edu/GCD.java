package ru.sberbank.edu;

public class GCD implements CommonDivisor {

    /***
     * Высчитывает наибольший общий делитель(divisor)
     * у двух целых чисел firstNumber и secondNumber
     * и возвращает его
     * Если secondNumber равен 0, возвращает -1
     * @param firstNumber
     * @param secondNumber
     * @return
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber){
        int divisor = 0;
        if (secondNumber == 0) {
            return -1;
        }
        while (firstNumber % secondNumber != 0) {
            divisor = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = divisor;
        }
        return divisor;
    }
}
