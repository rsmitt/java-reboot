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
        CustomArrayImpl<String> arrayList = new CustomArrayImpl<>();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);
        System.out.println( arrayList.get(0) );
        System.out.println( arrayList.get(1) );
        System.out.println( arrayList.get(2) );
        System.out.println( arrayList.get(3) );
        System.out.println( arrayList.get(4) );
        System.out.println("----------");
        int index = arrayList.indexOf(50);
        System.out.println( index );
        System.out.println("----------");
        System.out.println("----------");
        boolean inde = arrayList.contains(null);
        System.out.println( inde );
        System.out.println("----------");
        arrayList.reverse();
        System.out.println( arrayList.get(0) );
        System.out.println( arrayList.get(1) );
        System.out.println( arrayList.get(2) );
        System.out.println( arrayList.get(3) );
        System.out.println( arrayList.get(4) );
    }
}
