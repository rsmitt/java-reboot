package ru.sberbank.edu;
/**
 * @author bogdanzernovoj
 * @version 1.1
 * Поиск наибольшего общего делителя
 */
public class GCD implements CommonDivisor {
    /**
     * Метод возвращает наибольший общий делитель для firstNumber и secondNumber
     * @param firstNumber  целое число
     * @param secondNumber целове чило
     * @return наибольший общий делитель
     */
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
