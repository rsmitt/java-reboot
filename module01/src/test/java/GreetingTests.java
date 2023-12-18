import org.junit.jupiter.api.Test;
import ru.sberbank.edu.MyGreeting;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingTests  {
    /**
     * @author      Чернов Алексей
     * Класс с тестами для метода getBestHobby класса ru.sberbank.edu.MyGreeting
     */
    @Test
    public void greetingMainTest(){
        MyGreeting testGreeting = new MyGreeting();
        assertEquals(testGreeting.getBestHobby(), "My hobby is long-distance running");
    }

}
