package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        List<Integer> base = List.of(3,2,5,8,10,0,3);
        CustomDigitComparator cdc = new CustomDigitComparator();
        List<Integer> listOfInt = new ArrayList<>(base);
        listOfInt.sort(cdc);
        System.out.println(listOfInt);
        Person person1 = new Person("Ivan","Govnov", 20);
        Person person2 = new Person("Ivan","Govnov", 20);
        System.out.println(person2.hashCode());
        System.out.println(person1.hashCode());
    }
}
