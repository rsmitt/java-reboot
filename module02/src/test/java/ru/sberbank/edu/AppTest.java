package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Тест всего приложения
 */
class AppTest {
    private final String[] argsTypeD = new String[]{"D", "C:\\Users\\Cos\\IdeaProjects\\java-reboot\\module02\\src\\test\\test.txt", "C:\\Users\\Cos\\IdeaProjects\\java-reboot\\module02\\src\\test\\result.txt"};
    private final String[] argsTypeF = new String[]{"F", "C:\\Users\\Cos\\IdeaProjects\\java-reboot\\module02\\src\\test\\test.txt", "C:\\Users\\Cos\\IdeaProjects\\java-reboot\\module02\\src\\test\\result.txt"};

    @Test
    void mainTypeD() {
        assertAll(() -> App.main(argsTypeD));
    }

    @Test
    void mainTypeF() {
        assertAll(() -> App.main(argsTypeF));
    }
}