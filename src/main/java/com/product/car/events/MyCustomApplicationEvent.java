package com.product.car.events;

import org.springframework.context.ApplicationEvent;


public class MyCustomApplicationEvent extends ApplicationEvent {

    private String name;

    public MyCustomApplicationEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}