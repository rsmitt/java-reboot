package ru.sberbank.edu.repository;

import ru.sberbank.edu.model.Car;

import java.sql.SQLException;
import java.util.Set;

public interface CarRepository extends Repository<Car, String> {
    Set<Car> findByModel(String model) throws SQLException;
}
