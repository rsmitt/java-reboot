package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Student student = new Student("Ольга", "Джуманова");
        GreetingImpl greeting = new GreetingImpl("Коллекционираю монеты", student);
        System.out.println(greeting);
    }
}
