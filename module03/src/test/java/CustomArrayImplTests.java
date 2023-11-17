import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sberbank.edu.CustomArrayImpl;

public class CustomArrayImplTests {

    @Test
    public void customArraySizeTest() {
        CustomArrayImpl<String> customArray = new CustomArrayImpl<>();
        Integer firstSize = customArray.size();
        customArray.add("Abba");
        customArray.add("Check");
        Integer secondSize = customArray.size();

        Assertions.assertEquals(firstSize, 0);
        Assertions.assertEquals(secondSize, 2);
    }

    @Test
    public void customArrayIsEmptyTest() {
        CustomArrayImpl<String> customArray = new CustomArrayImpl<>();
        Boolean firstCheck = customArray.isEmpty();
        customArray.add("Abba");
        customArray.add("Check");
        Boolean secondCheck = customArray.isEmpty();

        Assertions.assertEquals(firstCheck, true);
        Assertions.assertEquals(secondCheck, false);
    }

    @Test
    public void customArrayCapacityTest() throws Exception {
        CustomArrayImpl<String> customArray = new CustomArrayImpl<>();
        Integer firstCheck = customArray.getCapacity();
        customArray.add("Abba");
        customArray.add("Check1");
        customArray.add("Check2");
        customArray.add("Check3");
        customArray.add("Check4");
        customArray.add("Check5");
        customArray.add("Check6");
        customArray.add("Check7");
        customArray.add("Check8");
        customArray.add("Check9");
        customArray.add("Check10");
        customArray.add("Check11");

        customArray.remove(1);
        Integer secondCheck = customArray.getCapacity();

        Assertions.assertEquals(firstCheck, 0);
        Assertions.assertEquals(secondCheck, 11);

    }

    @Test
    public void customArrayReverseTest(){
        CustomArrayImpl<String> customArray = new CustomArrayImpl<>();
        customArray.add("Check1");
        customArray.add("Check2");

        customArray.reverse();

        Assertions.assertEquals(customArray.get(0),"Check2");
        Assertions.assertEquals(customArray.get(1),"Check1");

    }


}
