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
     * Add car to database
     * @param id - car id
     * @param model - car model
     * @throws SQLException
     */
    @Override
    public void addCar(String id, String model) throws SQLException {
        carRepository.createOrUpdate(new Car(id, model));
    }

    /**
     * Edit exist car in database
     * @param id - car id
     * @param newModel - car new model for replace
     * @throws SQLException
     */
    @Override
    public void editModel(String id, String newModel) throws SQLException {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow();
        updateCarModel(car, newModel);
    }

    /**
     * Delete car from database
     * @param id - car id
     * @throws SQLException
     */
    @Override
    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    private void updateCarModel(Car car, String newModel) {
        car.setModel(newModel);
        try {
            carRepository.createOrUpdate(car);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
