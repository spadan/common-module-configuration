package com.example.multidatasource.properties;

import lombok.Data;

/**
 * 数据库连接属性
 *
 * @author xiongLiang
 * @date 2018/6/29 16:39
 */
@Data
public class DbConnectProperties {
    private String url;
    private String username;
    private String password;
}
