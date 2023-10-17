package ru.sberbank.edu;

import static org.junit.jupiter.api.Assertions.*;

class PersonSergeyChildinovTest {

    @Test
    void getBestHobby() {
        PersonSergeyChildinov sergey = PersonSergeyChildinov.getInstance();
        Assertions.assertTrue(sergey.getBestHobby().equals("Чтение старинных книг"));
    }

    @Test
    void getFullName() {
        PersonSergeyChildinov sergey = PersonSergeyChildinov.getInstance();
        Assertions.assertTrue(sergey.getFullName().equals("Чильдинов Сергей Михайлович"));
    }

    @Test
    void getCity() {
        PersonSergeyChildinov sergey = PersonSergeyChildinov.getInstance();
        Assertions.assertTrue(sergey.getCity().equals("Санкт-Петербург"));
    }

    @Test
    void getExperience() {
        PersonSergeyChildinov sergey = PersonSergeyChildinov.getInstance();
        Assertions.assertTrue(sergey.getExperience().equals("SAP, ABAP"));
    }
}