package com.example;

import com.example.event.MyEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        // 发布事件
        context.publishEvent(new MyEvent(new Object(), "你好，这里是模拟事件"));
    }

}
