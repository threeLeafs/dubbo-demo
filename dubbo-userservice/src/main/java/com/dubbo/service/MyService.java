package com.dubbo.service;

import com.dubbo.inter.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class MyService implements DemoService {

    @Value("${spring.application.name}")
    private String value;

    @Value("${server.port}")
    private String port;

    @Value("${timeout}")
    private int time;

    public String say(String message) {
        System.out.println(port);
       /* try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("new- 服务被调用，方法执行了......" + port);
        if(Math.random()>0.5){
            System.out.println("error- new- 服务被调用， 方法执行了......" + port);
            throw new RuntimeException();
        }
        return message+"-----invoke------"+port+"-----"+value;
    }
}
