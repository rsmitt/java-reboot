package test;

import org.testng.annotations.Test;
import ru.sberbank.edu.GreetingImpl;

import static org.junit.Assert.assertEquals;

public class GreetingImplTest {

    @Test
    public void getBestHobby() {
        GreetingImpl greetingImpl = new GreetingImpl();
        String ExpectedResult = "Имя: Дмитрий,\n" +
                "Фамилия: Карпов,\n" +
                "Должность: Инженер по нагрузочному тестированию,\n" +
                "Хобби: Увлекаюсь програмированием на Java, люблю активные виды спорта \n" +
                "(волейбол, настольный тенис, катание на велосипеде) и путешествия";
        assertEquals(ExpectedResult, greetingImpl.getBestHobby());

    }
}
