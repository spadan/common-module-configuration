package com.example.multidatasource.core;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源
 *
 * @author xiongLiang
 * @date 2018/6/29 16:49
 */
public class MultiDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.get();
    }
}
