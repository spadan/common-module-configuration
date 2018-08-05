package com.example.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * 线程池配置，配置该线程池后，异步方法将由该线程池负责执行
 *
 * @author xiongLiang
 * @date 2018/7/18 18:23
 */
@Configuration
@EnableConfigurationProperties(ExecutorProperties.class)
public class TaskExecutorConfig implements AsyncConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecutorConfig.class);
    @Autowired
    private ExecutorProperties properties;


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable arg0, Method arg1, Object... arg2) -> {
            logger.error("==========================" + arg0.getMessage() + "=======================", arg0);
            logger.error("exception method:" + arg1.getName());
        };
    }
}
