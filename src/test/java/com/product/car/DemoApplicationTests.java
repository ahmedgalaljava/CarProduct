package com.product.car;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.product.car.entities.Car;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class DemoApplicationTests {



	public  void getAllCarNotNull(){
		System.out.println("getAllCarNotNull Test");
		String allCars="http://localhost:8080/CarApp/all";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject carJsonObject = new JSONObject();

			Gson gson = new Gson();
			Type stringMap = new TypeToken<List<Car>>() {}.getType();
			String response = restTemplate.postForObject(allCars,null,String.class);
			List <Car> cars
					=gson.fromJson(response,stringMap);
		System.out.println("locationHeader>>>>>>>>>>>>>> "+cars);
		assertNotNull(cars);

	}



	public  void getAddNewCar(){
		System.out.println("getAllCarNotNull Test");
		String addNewCar="http://localhost:8081/CarApp/car/add";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate = new RestTemplate();
		JSONObject carJson = new JSONObject();
		try {
			carJson.put("name", "MyCar");
			carJson.put("price", 3000);
			carJson.put("imageURL", "https://imageio.forbes.com/specials-images/imageserve/5d35eacaf1176b0008974b54/2020-Chevrolet-Corvette-Stingray/0x0.jpg");
			HttpEntity<String> request = new HttpEntity<String>(carJson.toString(), headers);
			String carResultAsJsonStr =
					restTemplate.postForObject(addNewCar, request,String.class);

			System.out.println(carResultAsJsonStr);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}


	}
}
