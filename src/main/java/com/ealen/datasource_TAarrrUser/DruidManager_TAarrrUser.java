package com.ealen.datasource_TAarrrUser;

import com.alibaba.druid.pool.DruidDataSource;
import com.ealen.util.ReadPropertiesUtil;

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

        String username=ReadPropertiesUtil.get("datasource.taarrruser.username");
        dataSource.setUsername(username);
        String password=ReadPropertiesUtil.get("datasource.taarrruser.password");
        dataSource.setPassword(password);
        String url=ReadPropertiesUtil.get("datasource.taarrruser.url");
        dataSource.setUrl(url);

//        dataSource.setUsername("user_app_rtdata");
//        dataSource.setPassword("IdXnaCF1t-hP");
//        dataSource.setUrl("jdbc:mysql://rtd.mysql.ppdaidb.com:3423/ppdai_rtdata?useUnicode=true&characterEncoding=utf-8");

        dataSource.setInitialSize(5);
        dataSource.setMinIdle(0);
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