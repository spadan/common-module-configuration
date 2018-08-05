package com.example.event;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 事件监听器，监听到事件后异步执行
 *
 * @author xiongLiang
 * @date 2018/7/18 18:47
 */
@Async
@Component
public class MyListenerAsync implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(@Nullable MyEvent event) {
        System.out.println("我是异步线程："+Thread.currentThread().getName() + "----" + event.getMsg());
    }
}
