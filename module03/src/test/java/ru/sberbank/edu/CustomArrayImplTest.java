package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

class CustomArrayImplTest {

    private CustomArrayImpl<Object> customArray;//переменная хранения объекта для проверок

    @BeforeEach
    public void setUp() {
        customArray = new CustomArrayImpl<>();
    }

    @Test
    public void testAddIncreasesSize() {
        String item = "test";
        int initialSize = customArray.size();
        customArray.add(item);
        int finalSize = customArray.size();
        Assertions.assertThat(initialSize + 1).isEqualTo(finalSize);
    }

    @Test
    public void testAddItem() {
        String item = "test";
        customArray.add(item);
        Object firstItem = customArray.get(0);
        Assertions.assertThat(item).isEqualTo(firstItem);
    }

    @Test
    public void testIsEmpty() {

        Assertions.assertThat(customArray.isEmpty()).isTrue();
    }

    @Test
    public void testIsNotEmpty() {
        customArray.add(1);
        Assertions.assertThat(customArray.isEmpty()).isFalse();
    }

    @Test
    public void testAddAll() {
        Integer[] elements = {3, 2, 1};
        customArray.addAll(elements);
        for (Integer element : elements) {
            Assertions.assertThat(customArray.contains(element)).isTrue();
        }
    }

    @Test
    public void testRemove() {
        customArray.add("1");
        boolean result = customArray.remove("1");
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(customArray.isEmpty()).isTrue();
    }

    @Test
    public void testContains() {
        customArray.add(123);
        Assertions.assertThat(customArray.contains(123)).isTrue();
    }

}