package ru.sberbank.edu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GreetingImplTest {



    @Test
    void main() {
    }

    @Test
    void getBestHobby() {
        GreetingImpl greeting = new GreetingImpl();
        Assertions.assertThat(greeting.getBestHobby()).isEqualTo("Водный туризм");
    }
}