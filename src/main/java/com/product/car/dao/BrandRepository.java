package com.product.car.dao;

import com.product.car.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BrandRepository extends JpaRepository<Brand, Long> {


}
