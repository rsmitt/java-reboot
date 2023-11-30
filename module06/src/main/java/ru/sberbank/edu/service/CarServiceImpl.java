package ru.sberbank.edu.service;


import ru.sberbank.edu.model.Car;
import ru.sberbank.edu.repository.CarRepository;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Сервис
 */
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    /**
     * Констракутор
     * @param carRepository репозиторий
     */
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Добавить модель машины
     * @param id - идентификатор
     * @param model - модель
     * @throws SQLException особая ситуация
     */
    @Override
    public void addCar(String id, String model) throws SQLException {
        carRepository.createOrUpdate(new Car(id, model));
    }

    /**
     * Редактировать модель
     * @param id - идентификатор
     * @param newModel - модель
     * @throws SQLException особая ситуация
     */
    @Override
    public void editModel(String id, String newModel) throws SQLException {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow();
        updateCarModel(car, newModel);
    }

    /**
     * Удалить модель
     * @param id - идентификатор
     */
    @Override
    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    /**
     * Обновление модели
     * @param car объект машина
     * @param newModel новая модель
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
