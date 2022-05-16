package com.product.car.service;

import com.product.car.dao.BrandRepository;
import com.product.car.dao.CarRepository;
import com.product.car.entities.Brand;
import com.product.car.entities.Car;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.QueryHint;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceCar implements IServiceCar {
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

        Sort sortbynameasc = Sort.by(Sort.Direction.ASC, "name");
        Sort sortbypriceasc = Sort.by(Sort.Direction.ASC, "price");
        Sort sortbypricedes = Sort.by(Sort.Direction.DESC, "name");

        return carRepository.findByName(carName, sortbypriceasc);
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

    //Query
    public List<Car> findByBrandIds(List<Long> brandIds) {
        brandIds = new ArrayList<>();
        brandIds.add(1L);
        String queryString = "SELECT ids FROM Users WHERE ids NOT IN (";
//assuming idList is list of ids to exclude
        for (int i = 0; i < brandIds.size(); ++i)
            queryString += i == 0 ? "?" : ",?";
        queryString += ");";


        Query query = entityManager.createQuery(queryString, Car.class);

        for (int i = 0; i < brandIds.size(); ++i)
            query.setParameter(i, brandIds.get(i));
        List<Car> result = query.getResultList();

        return null;
    }




    public List<Car> findByBrandIdIn(List<Long> brandIds) {
        List<Car> cars = carRepository.findByBrandIdIn(brandIds);
        return cars;
    }

    public Car getCar(Long id) {

        Car car = carRepository.findById(id).get();

        return car;
    }

    public void saveCar(Car car) {

        carRepository.save(car);


    }

    public void addNewBrand(Brand brand) {


        brandRepository.save(brand);
    }
//Query
    public Car getCaryById(Long id) {
        Query jpqlQuery = entityManager.createQuery("SELECT c FROM Car c WHERE c.id=:id");
        jpqlQuery.setParameter("id",id);
        Car car= (Car) jpqlQuery.getSingleResult();
        return car;
    }

    //TypedQuery
    public Car getTypedQueryCaryById(Long id) {
        TypedQuery <Car> jpqlQuery = entityManager.createQuery("SELECT c FROM Car c WHERE c.id=:id",Car.class);

        TypedQuery <Car> namedjpqlQuery = entityManager.createNamedQuery("car.findallcars",Car.class);
        jpqlQuery.setParameter("id",id);
        Car car= jpqlQuery.getSingleResult();
        return car;
    }
    //TypedQuery
    public List<Car> getTypedQueryCaryAllCars() {
        TypedQuery <Car> jpqlQuery = entityManager.createQuery("SELECT c FROM Car c ORDER BY c.name",Car.class);


        return jpqlQuery.getResultList();
    }



}





