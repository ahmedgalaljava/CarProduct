package com.product.car.controller;

import com.product.car.entities.Car;
import com.product.car.service.ServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(("/CarApp"))
public class CarRestController {
    @Autowired
    ServiceCar serviceCar;

    @PostMapping("/all")
    public List<Car> getAllCars() {
        return serviceCar.getAllCars();
    }

    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable Long id) {
        return serviceCar.getCar(id);
    }

    @DeleteMapping("/car/delete/{id}")
    public  String deleteCar(@PathVariable Long id) {
        serviceCar.deleteCar(id);
        return "deleted";
    }

    @PostMapping(value = "/car/add",consumes = "application/json",produces ="application/json" )
    public Car addCar(@RequestBody Car car)
    {
       return serviceCar.persistCar(car);
    }

    @GetMapping("/car/brand/{id}")
    public Car findByBrandId (@PathVariable Long brandId) {
        List<Long> brands= new ArrayList<Long>();
        brands.add(brandId);
       return serviceCar.findByBrandIds(brands).get(0);
    }

}
