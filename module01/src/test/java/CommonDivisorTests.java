import org.junit.Test;
import ru.sberbank.edu.MyCommonDivisor;

import static org.junit.Assert.assertEquals;

public class CommonDivisorTests {

    MyCommonDivisor TestCommonDivisor = new MyCommonDivisor();

    @Test
    public void CommonDivisorTest_0_0() {

        assertEquals(TestCommonDivisor.getDivisor(0, 0), 0);
    }

    @Test
    public void CommonDivisorTest_5_50() {
        assertEquals(TestCommonDivisor.getDivisor(5, 50), 5);
    }

    @Test
    public void CommonDivisorTest_77_14() {
        assertEquals(TestCommonDivisor.getDivisor(77, 14), 7);
    }

    @Test
    public void CommonDivisorTest_13_13() {
        assertEquals(TestCommonDivisor.getDivisor(13, 13), 13);
    }

    @Test
    public void CommonDivisorTest_1_1() {
        assertEquals(TestCommonDivisor.getDivisor(1, 1), 1);
    }

}


