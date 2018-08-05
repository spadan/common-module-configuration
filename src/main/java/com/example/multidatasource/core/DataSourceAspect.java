package com.example.multidatasource.core;

import com.example.multidatasource.annotation.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * 数据源侦测切面
 *
 * @author xiongLiang
 * @date 2018/6/29 16:59
 */
@Aspect
@Order(-1)
public class DataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    // 假设所有mapper继承了mybatis plus的BaseMapper
    @Around("execution(public * com.baomidou.mybatisplus.mapper.BaseMapper+.*(..))")
    public Object aroundMapper(ProceedingJoinPoint point) throws Throwable {
        try {
            DataSource dataSource = point.getTarget().getClass().getInterfaces()[0].getAnnotation(DataSource.class);
            resetDataSource(dataSource);
            return point.proceed();
        } finally {
            DataSourceHolder.clear();
        }
    }

    private void resetDataSource(DataSource ds) {
        if (null == ds) {
            DataSourceHolder.clear();
            return;
        }
        String dbName = ds.value();
        if (!DataSourceHolder.contain(dbName)) {
            logger.error("数据源[{}]不存在，使用默认数据源", ds.value());
        } else {
            logger.debug("Use DataSource : {}", ds.value());
            DataSourceHolder.put(dbName);
        }
    }
}
