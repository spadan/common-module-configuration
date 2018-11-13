package com.example.orderservice.service;

import com.example.orderinterface.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${<DESCRIPTION>}
 *
 * @author xiongLiang
 * @date 2018/11/13 17:20
 */

@RestController
public class OrderServiceImpl implements OrderService {

    @Value("${server.port}")
    private String orderPort;

    @Override
    public String getPort() {
        return "order service port:" + orderPort;
    }

    @Override
    public String say(String name) {
        return "hello " + name;
    }
}
