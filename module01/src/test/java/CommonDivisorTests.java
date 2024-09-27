import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.sberbank.edu.MyCommonDivisor;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author Чернов Алексей
 * Класс с тестами для метода getDivisor класса ru.sberbank.edu.MyCommonDivisor
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommonDivisorTests {

    private static Stream<Arguments> testArguments() {
        return Stream.of(
                arguments(0, 0, 0),
                arguments(5, 50, 5),
                arguments(77, 14, 7),
                arguments(13, 13, 13),
                arguments(1, 1, 1)
        );
    }


    MyCommonDivisor TestCommonDivisor = new MyCommonDivisor();

    @ParameterizedTest
    @MethodSource("testArguments")
    public void CommonDivisorTest(int a, int b, int result) {

        Assertions.assertEquals(TestCommonDivisor.getDivisor(a, b), result);
    }

}