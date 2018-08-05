package com.example.springmvc.Controller;

import com.example.springmvc.Bean.JsonResult;
import com.example.springmvc.Bean.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${<DESCRIPTION>}
 *
 * @author xiongLiang
 * @date 2018/7/29 11:24
 */
@RestController
@RequestMapping("test")
public class MyController {

    @RequestMapping("hi1")
    public JsonResult<User> hello1() {
        User user = User.builder()
                        .name("xiongliang")
                        .age(28)
                        .address("江西吉安")
                        .company("深圳珍爱网")
                        .build();
        return JsonResult.<User>newBuilder()
                .code(0)
                .errorMessage("请求成功")
                .Data(user)
                .build();
    }

    @RequestMapping("hi2")
    public JsonResult<User> hello2(@Validated User user) {
        return JsonResult.<User>newBuilder()
                .code(0)
                .errorMessage("请求成功")
                .Data(user)
                .build();
    }


}
