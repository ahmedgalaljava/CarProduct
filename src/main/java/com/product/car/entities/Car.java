package com.product.car.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "image_url")
    private String imageURL;
    private BigDecimal price;

    public Brand getBrand() {
        return brand;
    }
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Brand brand;

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
