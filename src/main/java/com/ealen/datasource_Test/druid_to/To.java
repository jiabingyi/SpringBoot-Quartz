package com.ealen.datasource_Test.druid_to;

import com.ealen.entity.Table1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class To {

    public static  void  insertRow(List<Table1> list){
//        String sql = "insert into Table2 (c1,c2,c3) VALUES (?,?,?);";
        String sql = "replace into Table2 (c1,c2,c3,c4) VALUES (?,?,?,?);";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Druid连接池获取连接
            conn = DruidManagerTo.getInstance().getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < list.size(); i++) {
                ps.setInt(1,list.get(i).getC1());
                ps.setString(2,list.get(i).getC2());
                ps.setString(3,list.get(i).getC3());
                ps.setDate(4,list.get(i).getC4());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭声明
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 关闭链接对象
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
