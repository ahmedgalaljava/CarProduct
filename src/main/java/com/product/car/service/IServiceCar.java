package com.product.car.service;

import com.product.car.entities.Brand;
import com.product.car.entities.Car;

import java.util.List;

public interface IServiceCar {
    public List<Car> getAllCars();
    public List<Car> findByName(String carName);
    public Car persistCar(Car car);
    public List<Brand> getAllBrands();

    void deleteCar(Long carId);
}
