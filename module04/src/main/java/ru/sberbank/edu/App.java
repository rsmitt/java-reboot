package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

        List<Integer> intList = new ArrayList<>();

        intList.add(1);
        intList.add(2);
        intList.add(2);
        intList.add(1);

        Comparator<Integer>  comporator = new CustomDigitComparator();
        intList.sort(comporator);

        System.out.println(intList);



        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Имя","Город",15));
        personList.add(new Person("иМя","Город123",15));
        personList.add(new Person("имЯ1","Город",15));
        personList.add(new Person("имя","Город",15));

        personList.sort(Person::compareTo);

        System.out.println(personList);
    }
}
