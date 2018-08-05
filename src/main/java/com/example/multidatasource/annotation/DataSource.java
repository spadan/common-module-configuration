package com.example.multidatasource.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 *
 * @author xiongLiang
 * @date 2018/6/29 17:04
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String DEFAULT_DB_NAME = "default";

    /**
     * 指定数据源
     */
    String value() default DEFAULT_DB_NAME;
}
