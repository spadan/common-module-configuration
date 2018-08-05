package com.example.async;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池属性实体类
 *
 * @author xiongLiang
 * @date 2018/7/18 18:25
 */
@Data
@ConfigurationProperties(prefix = "spring.task.pool")
public class ExecutorProperties {
    //核心线程数
    private int corePoolSize = 5;

    //最大线程数
    private int maxPoolSize = 50;

    //线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 60;

    //队列长度
    private int queueCapacity = 10000;

    //线程名称前缀
    private String threadNamePrefix = "AsyncTask-";
}
