package ru.sberbank.edu;

import junit.framework.TestCase;

import java.util.Random;

/**
 * <p>Тестовый класс для GreetingImpl</p>
 */
public class GreetingImplTest extends TestCase {
    Greeting greeting = null;
    String testHobby = "";

    /**
     * <p>Инициализация объектов и переменных перед темтом</p>
     * @throws Exception
     */
    public void setUp() throws Exception {
        testHobby = generateRandomString();
        greeting = new GreetingImpl(testHobby);
        super.setUp();
    }

    /**
     * <p>Тест метода getBestHobby</p>
     */
    public void testGetBestHobby() {
        assertEquals(testHobby, greeting.getBestHobby());
    }

    /**
     * <p>Генератор случайной строки - 10 символов</p>
     * @return случайная строка
     */
    private String generateRandomString() {
        int leftLimit = 97; // буква 'a'
        int rightLimit = 122; // буква 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}