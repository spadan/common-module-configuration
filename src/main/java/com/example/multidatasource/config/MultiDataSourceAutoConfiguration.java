package com.example.multidatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.multidatasource.core.DataSourceAspect;
import com.example.multidatasource.core.DataSourceHolder;
import com.example.multidatasource.core.MultiDataSource;
import com.example.multidatasource.properties.DataSourceProperties;
import com.example.multidatasource.properties.DbConnectProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid数据源自动配置
 *
 * @author xiongLiang
 * @date 2018/6/29 17:11
 */
//@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class MultiDataSourceAutoConfiguration {

    @Autowired
    private DataSourceProperties properties;

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiDataSourceAutoConfiguration.class);

    @Bean
    public DataSourceAspect dataSourceAspect() {
        return new DataSourceAspect();
    }

    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DataSource dataSource() {
        LOGGER.info("auto configuration ==================> druid datasource");
        // 多数据源容器
        Map<Object, Object> multiSources = new HashMap<>();
        // 默认数据源
        DruidDataSource defaultDatasource = new DruidDataSource();
        BeanUtils.copyProperties(properties, defaultDatasource);
        defaultDatasource.setConnectionInitSqls(Collections.singletonList(properties.getConnectionInitSql()));
        multiSources.put(com.example.multidatasource.annotation.DataSource.DEFAULT_DB_NAME, defaultDatasource);
        DataSourceHolder.add(com.example.multidatasource.annotation.DataSource.DEFAULT_DB_NAME);
        // 其他数据源
        if (null != properties.getMulti()) {
            DruidDataSource dataSource;
            for (Map.Entry<String, DbConnectProperties> druidMultiPropEntry : properties.getMulti().entrySet()) {
                if (validMultiProp(druidMultiPropEntry)) {
                    dataSource = new DruidDataSource();
                    BeanUtils.copyProperties(properties, dataSource);
                    String name = druidMultiPropEntry.getKey();
                    DbConnectProperties druidMultiProp = druidMultiPropEntry.getValue();
                    dataSource.setConnectionInitSqls(Collections.singletonList(properties.getConnectionInitSql()));
                    DataSourceHolder.add(name);
                    dataSource.setUrl(druidMultiProp.getUrl());
                    dataSource.setUsername(druidMultiProp.getUsername());
                    dataSource.setPassword(druidMultiProp.getPassword());
                    multiSources.put(name, dataSource);
                }
            }
        }
        MultiDataSource multiDatasource = new MultiDataSource();
        multiDatasource.setDefaultTargetDataSource(defaultDatasource);
        multiDatasource.setTargetDataSources(multiSources);
        return multiDatasource;
    }

    /**
     * 是否是有效的配置值
     *
     * @author maidi
     * @date 2017年10月13日
     * @param druidMultiPropEntry
     * @return
     */
    private boolean validMultiProp(Map.Entry<String, DbConnectProperties> druidMultiPropEntry) {
        if (null == druidMultiPropEntry) {
            return false;
        }
        if (com.example.multidatasource.annotation.DataSource.DEFAULT_DB_NAME.equals(druidMultiPropEntry.getKey())) {
            return false;
        }
        DbConnectProperties druidMultiProp = druidMultiPropEntry.getValue();
        if (null == druidMultiProp) {
            return false;
        }
        if (StringUtils.isBlank(druidMultiProp.getUrl())) {
            return false;
        }
        if (StringUtils.isBlank(druidMultiProp.getUsername())) {
            return false;
        }
        if (StringUtils.isBlank(druidMultiProp.getPassword())) {
            return false;
        }
        return true;
    }
}
