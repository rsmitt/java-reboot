package ru.sberbank.edu;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomArrayImpl<Integer> arrayList = new CustomArrayImpl();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add("jf");
        System.out.println( arrayList.get(2) );
    }
}
