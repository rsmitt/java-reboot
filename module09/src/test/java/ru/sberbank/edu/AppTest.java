package ru.sberbank.edu;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigourous Test :-)
     */
    @Test
    void testApp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        assertTrue(true);
    }
}
