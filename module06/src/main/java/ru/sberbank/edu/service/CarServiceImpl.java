package ru.sberbank.edu.service;


import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarRepository;

import java.sql.SQLException;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Метод добавляет авто в таблицу CAR
     * принимает на вход строковое значение id и название модели
     */
    @Override
    public void addCar(String id, String model) throws SQLException {
        carRepository.createOrUpdate(new Car(id, model));
    }

    /**
     * Метод изменяет модель авто в таблице CAR
     * принимает на вход строковое значение id по которому осуществляется поиск авто
     * и новое название модели
     */
    @Override
    public void editModel(String id, String newModel) throws SQLException {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow();
        updateCarModel(car, newModel);
    }

    /**
     * Метод удаляет авто из таблице CAR
     * принимает на вход строковое значение id удаляемой позиции
     */
    @Override
    public void deleteCar(String id) throws SQLException {
        carRepository.deleteById(id);

    }

    /**
     * Метод обновляет модель авто в таблице CAR
     * принимает на вход объект типа car и строковое значение нового названия модели
     */
    private void updateCarModel(Car car, String newModel) {
        car.setModel(newModel);
        try {
            carRepository.createOrUpdate(car);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
