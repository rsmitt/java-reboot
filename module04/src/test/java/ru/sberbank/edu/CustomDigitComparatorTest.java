package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomDigitComparatorTest {
    @Test
    void checkCompareTest(){
        CustomDigitComparator comparator = new CustomDigitComparator();
        int even = comparator.compare(2,3);
        assertEquals(-1,even);
        int notEven = comparator.compare(-5,2);
        assertEquals(1,notEven);
        int equal = comparator.compare(-2,2);
        assertEquals(0,equal);
        int zero = comparator.compare(0,0);
        assertEquals(0,zero);

    }
}
