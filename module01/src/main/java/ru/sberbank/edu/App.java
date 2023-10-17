package ru.sberbank.edu;
import java.util.Scanner;
/**
 * @author bogdanzernovoj
 * @version 1.1
 * Домашнее задание module01
 */
public class App
{
    public static void main( String[] args )
    {
        int firstNumber, secondNumber;


        Scanner scanner = new Scanner(System.in);

        Greeting loGreeting = new GreetingImpl( "Читать книжки");
        System.out.println( loGreeting.getBestHobby() );

        CommonDivisor loCommonDivisor = new GCD();
        System.out.println( "Введите целое число" );
        firstNumber = scanner.nextInt();

        System.out.println( "Введите целое число" );
        secondNumber = scanner.nextInt();
        System.out.print( "Наибольший общий делитель для " + firstNumber + " и " + secondNumber + " = ");
        System.out.println( loCommonDivisor.getDivisor( firstNumber, secondNumber ) );

    }
}
