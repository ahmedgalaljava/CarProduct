package com.product.car.events;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
    private String name ;
    public MyEvent(Object source,String name) {
        super(source);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
