package com.product.car.controller;

import com.product.car.entities.Brand;
import com.product.car.entities.Car;
import com.product.car.service.ServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/car")
public class AppController {
    @Autowired
    private ServiceCar service;
    private String uploadDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\assets\\images\\cars";

    @GetMapping ("/menu")
    public String menuPage() {
        return "index";
    }

    @GetMapping ("/edit")
    public String editPage(Model m,@RequestParam(required = false) Long id) {
        Car car = service.getCar(id);
        m.addAttribute("car", car);
        return "edit";
    }

    @PostMapping ("/edit")
    public String editPagePost(Model model,Car car) {
        service.persistCar(car);
        model.addAttribute("car", car);
        return "edit";
    }

    @GetMapping ("/admin")
    public String adminPage(Model m) {
        List <Car>listCars = service.getAllCars();
        m.addAttribute("listCars", listCars);
        return "admin";
    }



    @GetMapping ("/new-purchase")
    public String newPurchase(Model model,@RequestParam(required = false) String ids,@RequestParam(required = false)  String carname) {

        if (carname !=null ) {
            model.addAttribute("brands", service.getAllBrands());
            List<Car> listCars=null;
            if (carname.equals("all")) {
                listCars=service.getAllCars();
                model.addAttribute("listCars", listCars);

            }
            else {
                listCars=service.findByName(carname);
                model.addAttribute("listCars", listCars);
            }
            return "purchase_new";
        }
        List<Car> listCars =null ;
        String [] idsarray;
        List<Long>brandIds;
        if (ids !=null) {
            idsarray = ids.split(",");
            brandIds = new ArrayList<>();


            for (int i = 0; i < idsarray.length; i++) {
                if (idsarray[i] != null && !idsarray[i].isBlank() && !idsarray[i].isEmpty()) {
                    brandIds.add(new Long(idsarray[i]));
                }
            }
            listCars= service.findByBrandIdIn(brandIds);
        }

        else {
            listCars = service.getAllCars();


        }

        model.addAttribute("listCars", listCars);
        model.addAttribute("brands", service.getAllBrands());
        System.out.println("sizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" + service.getAllBrands().size());


        return "purchase_new";
    }


    @GetMapping ("/addnewbrand")
    public String addnewbrand(Model m){
        return "addnewbrand";

    }


    @PostMapping  ("/addnewbrand")
    public String addnewbrand(Brand brand){

         service.addNewBrand(brand);
         return "addnewbrand";

    }


    @GetMapping ("/add")
    public String add(Model m){

        m.addAttribute("brands", service.getAllBrands());
        List <Car>listCars = service.getAllCars();
        m.addAttribute("listCars", listCars);
        return "add";

    }

    @PostMapping("/add")
    public String add1(Car car) {

        service.persistCar(car);

        return "redirect:/car/new-purchase";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {

        service.deleteCar(id);

        return "redirect:/car/admin";
    }

}
