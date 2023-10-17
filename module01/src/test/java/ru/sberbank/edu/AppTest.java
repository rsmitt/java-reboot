package ru.sberbank.edu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Это Юнит-тест для проверки моего хобби. Берется только 1 хобби. Подразумевается, если оно стоит первым, то у него наивысший приоритет.
 */
public class AppTest
{
    /**
     * Это тело теста. Здесь создается персона (то есть я) и несколько хобби, мне присваиваются 2 и потом берет 1 из них.
     */
    @Test
    public void testHobby()
    {
        Person me = new Person("I", "am", "34");

        Hobby h1 = new Hobby("Soccer", "Pushing ball on grass");
        Hobby h2 = new Hobby("Programming", "Java, Python, SQL");
        Hobby h3 = new Hobby("Swimming", "Water is good");
        Hobby h4 = new Hobby("Getting lazy", "It's amazing");

        ArrayList<String> list = new ArrayList();
        list.add(h2.getHobbyName());
        list.add(h1.getHobbyName());
        GreetingImpl corr = new GreetingImpl();
        corr.setGreetingImpl(me, new ArrayList(list));
        System.out.println("myHobby is " + corr.getBestHobby());
        Assertions.assertEquals(corr.getBestHobby(), "Programming");
    }
}
