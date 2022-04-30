package com.product.car.dao;

import com.product.car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository  extends JpaRepository<Car, Long> {

    List<Car> findByName(String carName);
    List<Car> findByImageURLIsNotNull();
    List<Car> findByNameContaining (String prfix);
    List<Car> findByPriceLessThan (BigDecimal price);
    List<Car> findByNameOrBrandName (String carName, String brandName);
    List <Car> findByNameOrderByPriceDesc(String carName);
    List <Car> findByBrandIdIn(List<Long> brandIds);
    



}
