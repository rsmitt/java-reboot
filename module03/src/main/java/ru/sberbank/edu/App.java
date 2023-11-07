package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomArrayImpl<String> arrayList = new CustomArrayImpl<>(0);
        Integer[] val = new Integer[] {1,2,3,4,5,6,7,8};
        arrayList.add(10);
        System.out.println(arrayList.size());
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);
        System.out.println(arrayList.size());
        arrayList.remove(3);
        System.out.println(arrayList.size());
        System.out.println(arrayList.getCapacity());
        System.out.println("----------");
        arrayList.addAll(2, val);
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println("----------");
        int index = arrayList.indexOf(50);
        System.out.println( index );
        System.out.println( arrayList.getCapacity() );
        System.out.println("----------");
        System.out.println("----------");
        boolean inde = arrayList.contains(null);
        System.out.println( inde );
        System.out.println("----------");
        arrayList.reverse();
        for (int i = 0; i < arrayList.getCapacity(); i++) {
            System.out.println(arrayList.get(i));
        }
    }
}
