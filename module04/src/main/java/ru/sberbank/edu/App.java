package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Random rand = new Random();
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i< 100; i++) {
            digits.add(i * rand.nextInt(10));
        }
        System.out.println(digits);
        Comparator<Integer> cmp = new CustomDigitComparator();
        digits.sort(cmp);
        System.out.println(digits);


        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Tinky", "Krasnodar", 38));
        persons.add(new Person("Vinki", "Habarovsk", 29));
        persons.add(new Person("Po", "Norilsk", 43));


        System.out.println(persons);
    }
}
