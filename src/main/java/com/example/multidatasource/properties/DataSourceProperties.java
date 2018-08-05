package com.example.multidatasource.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 数据源属性
 *
 * @author xiongLiang
 * @date 2018/6/29 16:42
 */
@ConfigurationProperties(prefix = "datasource")
@Data
public class DataSourceProperties {
    private String driverClassName;
    // 默认数据源配置
    private String url;
    private String username;
    private String password;
    // 连接池配置
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private int queryTimeout;
    private String validationQuery;
    private boolean defaultReadOnly;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String connectionInitSql;
    // 其他数据源配置
    private Map<String, DbConnectProperties> multi;
}
