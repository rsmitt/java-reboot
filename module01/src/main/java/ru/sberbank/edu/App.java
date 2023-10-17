package ru.sberbank.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Привет мир!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println("Привет мир!");
        PersonSergeyChildinov sergey = PersonSergeyChildinov.getInstance();
        System.out.println(sergey);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Нахождение НОД");
            System.out.println("Введите первое число : ");
            int a = Integer.parseInt(br.readLine());
            System.out.println("Введите второе число : ");
            int b = Integer.parseInt(br.readLine());
            GCD c = new GCD();
            System.out.println( "1-е число : " + a + "\n" +
                    "2-е число : " + b + "\n" +
                    "Наибольший общий делитель : " + c.getDivisor(a,b));

        }
        catch (IOException e) {
            System.out.println("Ощибка ввода/вывод");
        }
    }
}
