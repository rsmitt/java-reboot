package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list);
        Comparator<Integer> comp = new CustomDigitComparator();
        list.sort(comp);
        System.out.println(list);
        /*Person person1 = new Person("Oleg","Moscow",15);
        Person person2 = new Person("Egor","Kasimov",16);
        Person person3 = new Person("Egor","KaSImov",16);

        System.out.println(person1.toString());
        System.out.println(person2.toString());
        System.out.println(person3.toString());

        System.out.println(person2.equals(person1));
        System.out.println(person2.equals(person3));

        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(person3.hashCode());
         */

    }
}
