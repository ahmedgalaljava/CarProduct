package com.product.car.controller;

import com.product.car.entities.Brand;
import com.product.car.entities.Car;
import com.product.car.service.ServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private ServiceCar service;

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


          /**  for (int i = 0; i < idsarray.length; i++) {
                if (idsarray[i] != null && !idsarray[i].isBlank() && !idsarray[i].isEmpty()) {
                    brandIds.add(new Long(idsarray[i]));
                }
            } **/
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

}
