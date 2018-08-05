package com.example.multidatasource.core;

import java.util.HashSet;
import java.util.Set;

/**
 * ${<DESCRIPTION>}
 *
 * @author xiongLiang
 * @date 2018/6/29 16:50
 */
public class DataSourceHolder {

    private static ThreadLocal<String> targetDataSource = new ThreadLocal<>();
    private static Set<String> dbNames = new HashSet<>();

    public static String get() {
        return targetDataSource.get();
    }

    public static void put(String lookupKey) {
        targetDataSource.set(lookupKey);
    }

    public static void clear() {
        targetDataSource.remove();
    }

    public static void add(String dbName) {
        dbNames.add(dbName);
    }

    public static boolean contain(String dbName) {
        return dbNames.contains(dbName);
    }


}
