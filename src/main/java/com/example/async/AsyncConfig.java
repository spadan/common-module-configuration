package com.example.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 开启异步方法配置，@Async注解的方法调用时异步执行
 *
 * @author xiongLiang
 * @date 2018/7/18 17:48
 */
@Configuration
@EnableAsync
public class AsyncConfig {
}
