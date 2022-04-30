package com.product.car.service;

import com.product.car.dao.BrandRepository;
import com.product.car.dao.CarRepository;
import com.product.car.entities.Brand;
import com.product.car.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceCar implements IServiceCar{
    @Autowired
    CarRepository carRepository;
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findByName(String carName) {
        return carRepository.findByName(carName);
    }

    @Override
        public Car persistCar(Car car) {
        return carRepository.save(car);
        }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }


    public List<Car> findByBrandIds(List<Long>brandIds) {
        brandIds= new ArrayList<>();
        brandIds.add(1L);
        String queryString = "SELECT ids FROM Users WHERE ids NOT IN (";
//assuming idList is list of ids to exclude
        for (int i =0; i<brandIds.size();++i)
            queryString +=i==0?"?":",?";
        queryString +=");";


        Query query = entityManager.createQuery(queryString, Car.class);

        for (int i =0; i<brandIds.size();++i)
            query.setParameter(i, brandIds.get(i));
        List<Car> result= query.getResultList();

        return null;
    }


    public  List<Car> findByBrandIdIn(List<Long> brandIds) {
        List<Car> cars=carRepository.findByBrandIdIn(brandIds);
        return cars;
    }
   public Car getCar(Long id) {

       Car car= carRepository.findById(id).get();

       return  car;
   }

    public void saveCar(Car car) {

        carRepository.save(car);


    }
    public void addNewBrand(Brand brand) {

        brandRepository.save(brand);
    }


}
