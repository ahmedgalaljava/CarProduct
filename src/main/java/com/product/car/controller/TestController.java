package com.product.car.controller;

import com.product.car.events.MyCustomApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ApplicationEventPublisher publisher;

    @GetMapping(value = "/publish")
    public String publish() {
        System.out.println("TestController : publishing the message");

        MyCustomApplicationEvent event = new MyCustomApplicationEvent(this, "MyEvent");
       // System.out.println("MyCustomApplicationEventPublisher : Source : "+this.getClass().getName() + ", Message : "+ event.getName());
        publisher.publishEvent(event);

       // System.out.println("TestController : published the message");
        return "Published";
    }
}
