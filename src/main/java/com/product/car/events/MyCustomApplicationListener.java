package com.product.car.events;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

@Configuration
@Async
public class MyCustomApplicationListener {

    @EventListener
    @Order(1)
    @Async
    public void listener1(MyCustomApplicationEvent event) {

        System.out.println("Listener1  " + event.getName());
    }

    @EventListener
    @Order(2)
    @Async
    public void listener2(MyCustomApplicationEvent event) {

        System.out.println("Listener2      : Source : "+event.getSource().getClass().getName() + ", Message : "+ event.getName());
    }

    @EventListener
    @Order(3)
    @Async
    public void listener3(MyCustomApplicationEvent event) {

        System.out.println("Listener3      : Source : "+event.getSource().getClass().getName() + ", Message : "+ event.getName());
    }
}
