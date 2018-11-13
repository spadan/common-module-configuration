package com.example.orderapi;

import com.example.orderapi.service.OrderServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
@RequestMapping("/order")
public class OrderApiApplication {

    private final OrderServiceFeign orderService;

    @Value("${server.port}")
    private String port;

    @Autowired
    public OrderApiApplication(OrderServiceFeign orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApiApplication.class, args);
    }


    @GetMapping("api-port")
    public String getApiPort() {
        return "order api port:" + port;
    }

    @GetMapping("service-port")
    public String getServicePort() {
        return orderService.getPort();
    }

    @GetMapping("say")
    public String say(String name) {
        return orderService.say(name);
    }

}
