package com.product.car;

import com.product.car.controller.AppController;
import com.product.car.dao.CarRepository;
import com.product.car.entities.Brand;
import com.product.car.entities.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@ComponentScan({"com.product.car"})
public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CarRepository repository) {
		return (args) -> {

		List <Car> carListByName=repository.findByNameOrderByPriceDesc("car2");
		for (Car car : carListByName) {

			System.out.println(car.getPrice());

		}

		};

	}

}
