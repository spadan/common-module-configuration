package com.example.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 *
 * @author xiongLiang
 * @date 2018/7/18 18:44
 */
public class MyEvent extends ApplicationEvent {
    private String msg;

    public MyEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
