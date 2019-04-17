package com.ealen.datasource_Test.druid_from;

import com.ealen.entity.Table1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class From {

    public static List<Table1>  queryByRowNum(int first,int end){
        List<Table1> list=new ArrayList<Table1>();
//        String sql = "SELECT * FROM Table1  where c4='2018-08-08'  order by c1 asc limit "+first+","+end;
        String sql = "SELECT * FROM Table1  where c4='2019-10-20'  order by c1 asc limit "+first+","+end;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Druid连接池获取连接
            conn = DruidManagerFrom.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println(" : " + rs.getInt(1) + "  : "
//                        + rs.getString(2) + "  : " + rs.getString(3)+"  : " + rs.getDate(4));
                list.add(new Table1(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭记录集
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

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
        return list;
    }


    public static void main(String[] args) {
        insert();
    }

    public static  void  insert(){
        String sql = "replace into Table1 (c1,c2,c3,c4) VALUES (?,?,?,'2019-10-20');";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Druid连接池获取连接
            conn = DruidManagerFrom.getInstance().getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < 1000; i++) {
                ps.setInt(1,i+20);
                ps.setString(2,"第"+(i+20)+"行,第1列");
                ps.setString(3,"第"+(i+20)+"行,第2列");
//                ps.setDate(4, new java.sql.Date());
//                ps.setDate(4, "2018-10-20");
//                ps.setDate(4, System.currentTimeMillis());
//                ps.setDate(4, (java.sql.Date) new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-10"));
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
