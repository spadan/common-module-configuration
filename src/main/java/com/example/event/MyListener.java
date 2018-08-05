package com.example.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器，监听到事件后同步执行
 *
 * @author xiongLiang
 * @date 2018/7/18 18:47
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("我是同步线程：" + Thread.currentThread().getName() + "----" + event.getMsg());
    }
}
