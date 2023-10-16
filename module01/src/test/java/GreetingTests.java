import org.junit.Test;
import ru.sberbank.edu.MyGreeting;

import static org.junit.Assert.assertEquals;

public class GreetingTests  {

    @Test
    public void greetingMainTest(){
        MyGreeting testGreeting = new MyGreeting();
        assertEquals(testGreeting.getBestHobby(), "My hobby is long-distance running");
    }

}
