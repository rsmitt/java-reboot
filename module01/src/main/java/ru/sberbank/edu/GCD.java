package ru.sberbank.edu;

/**
 *
 */
public class GCD implements CommonDivisor{
    /**
     * <p>Расчет наибольшего общего делителя (НОД) по алгоритму Евклида</p>
     * @param firstNumber - первое число
     * @param secondNumber - второе число
     * @return НОД
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        return secondNumber == 0 ? firstNumber : getDivisor(secondNumber, firstNumber % secondNumber);
    }
}
