package com.ealen.datasource_TAarrrUser;

import com.alibaba.druid.pool.DruidDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DruidManager_TAarrrUser {

    private static DruidManager_TAarrrUser druidManager = null;
    private DruidDataSource dataSource;

    public synchronized static DruidManager_TAarrrUser getInstance() {
        if (druidManager == null) {
            druidManager = new DruidManager_TAarrrUser();
            druidManager.initPool();
        }
        return druidManager;
    }


    private void initPool() {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("Gm2i9$QT$");
        dataSource.setUrl("jdbc:mysql://10.114.27.179:3306/hello_test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false");
//        dataSource.   setUsername(Config.USER);
//        dataSource.   setPassword(Config.PASSWORD);

        dataSource.setInitialSize(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        // 启用监控统计功能
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
//　　　　　　　　thorw new ExceptionInInitializerError(e);
        }
        dataSource.setPoolPreparedStatements(false);
    }


    //要考虑多线程的情况
    public Connection getConnection() {
        Connection connection = null;
        try {
            synchronized (dataSource) {
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
//　　　　　　　　throw new Exception(e);
        }
        return connection;
    }


}  