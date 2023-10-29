package ru.sberbank.edu;

public class App {
    public static void main( String[] args ) {
//        CustomDigitComparator comparator = new CustomDigitComparator();
//        System.out.println(comparator.compare(1, 2));
//        System.out.println(comparator.compare(2, 1));
//        System.out.println(comparator.compare(1, 1));
//        System.out.println(comparator.compare(2, 2));
//        System.out.println(comparator.compare(2, null));

        Person person1 = new Person("Kays", "Moscow", 30);
        Person person2 = new Person("Akhmad", "Tver", 54);
        System.out.println(person1.compareTo(person2));
    }
}
