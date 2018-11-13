package com.example.orderinterface.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单服务接口
 *
 * @author xiongLiang
 * @date 2018/11/13 17:14
 */

@RequestMapping("/order-service")
public interface OrderService {

    @GetMapping("/port")
    String getPort();

    @GetMapping("say")
    String say(@RequestParam("name") String name);

}
