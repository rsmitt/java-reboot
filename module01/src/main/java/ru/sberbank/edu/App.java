package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Greeting greeting = new GreetingImpl("Нету. На хобби нет времени");
        System.out.println( greeting.getBestHobby() );
        CommonDivisor commonDivisor = new GCD();
        System.out.println("НОД(6,3) = " + commonDivisor.getDivisor(6,3));
    }
}
