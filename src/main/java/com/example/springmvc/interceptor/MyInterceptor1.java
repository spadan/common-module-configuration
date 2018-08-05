package com.example.springmvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author xiongLiang
 * @date 2018/7/29 15:32
 */

@Component
public class MyInterceptor1 extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("方法调用开始");
        return true;
    }

    /**
     * 重写postHandle方法，在请求完成之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("方法调用结束");
    }

}
