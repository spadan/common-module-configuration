package com.example.orderapi.service;

import com.example.orderinterface.service.OrderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * ${<DESCRIPTION>}
 *
 * @author xiongLiang
 * @date 2018/11/13 17:36
 */

@FeignClient("order-service")
@Component
public interface OrderServiceFeign extends OrderService {
}
