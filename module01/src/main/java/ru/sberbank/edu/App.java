package ru.sberbank.edu;

public class App 
{
    public static void main( String[] args ) {
        GreetingImpl greeting = new GreetingImpl();
        System.out.println(greeting.getBestHobby());
        GCD gcd = new GCD();
        System.out.println(gcd.getDivisor(0, 0));
        System.out.println(gcd.getDivisor(-100, 20));
        System.out.println(gcd.getDivisor(7, 29));
    }
}
