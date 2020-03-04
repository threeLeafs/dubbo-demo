package com.dubbo.controller;

import com.dubbo.inter.DemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    //@Reference(cluster="failover",timeout = 3000)
    @Reference(check = true,timeout = 3000,retries = 2)
    private DemoService service;

    @Value("${spring.application.name}")
    private String message;

    @GetMapping("/say")
    @HystrixCommand(fallbackMethod = "testError")
    public String say(){
        return  service.say(message);
    }


    public String testError(){
        return message+"：客官请稍后，忙中.....";
    }

}
