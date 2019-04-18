package com.ealen.datasource_TAarrrUser.druid_from;



import com.ealen.datasource_TAarrrUser.DruidManager_TAarrrUser;
import com.ealen.entity.TAarrrUser;
import com.ealen.sendMail.mailutil_ppd.SendMailUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class From {

    public static List<TAarrrUser>  queryByRowNum(int first, int rows,String startDate,String endDate){
        List<TAarrrUser> list= new ArrayList<>();
        String sql = "SELECT id,user_id,user_name,register_time,source_id,source_name,channel_type,channel_type2,channel_name,channel_id,auth_time,auth_count,auth_count7,auth_count30,cardbind_time," +
                "cardbind_count,cardbind_count7,cardbind_count30,recharge_time,recharge_amount,recharge_amount7,recharge_amount30,invest_time,first_invest_amount,invest_amount,invest_amount7,invest_amount30,invest_bulk_time," +
                "invest_bulk_amount,invest_bulk_amount7,invest_bulk_amount30,invest_rainbow_time,invest_rainbow_amount,invest_rainbow_amount7,invest_rainbow_amount30,invest_rainbowplus_time,invest_rainbowplus_amount,invest_rainbowplus_amount7,invest_rainbowplus_amount30,invest_monthshare_time,invest_monthshare_amount,invest_monthshare_amount7,invest_monthshare_amount30,invest_debt_time,invest_debt_amount,invest_debt_amount7,invest_debt_amount30,"+
                "register_fail_reqcnt,auth_fail_reqcnt,auth_fail_reqcnt7,auth_fail_reqcnt30,cardbind_succ_reqcnt,cardbind_succ_reqcnt7,cardbind_succ_reqcnt30,cardbind_fail_reqcnt,cardbind_fail_reqcnt7,cardbind_fail_reqcnt30,"+
                "recharge_fail_reqcnt,recharge_fail_reqcnt7,recharge_fail_reqcnt30,recharge_succ_reqcnt,recharge_succ_reqcnt7,recharge_succ_reqcnt30,inserttime,updatetime,isactive" +
                " FROM t_aarrr_user  where inserttime>='"+startDate+"' and inserttime<='"+endDate+"'  order by id asc limit "+first+","+rows;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Druid连接池获取连接
            conn = com.ealen.datasource_Test.druid_from.DruidManagerFrom.getInstance().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println(" : " + rs.getInt(1) + "  : "
//                        + rs.getString(2) + "  : " + rs.getString(3)+"  : " + rs.getDate(4));
//                list.add(new TAarrrUser(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4)));
                list.add(new TAarrrUser(
                        rs.getLong(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4),rs.getInt(5),
                        rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),
                        rs.getTimestamp(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getTimestamp(15),
                        rs.getInt(16),rs.getInt(17),rs.getInt(18),rs.getTimestamp(19),rs.getDouble(20),
                        rs.getDouble(21),rs.getDouble(22),rs.getTimestamp(23),rs.getDouble(24),rs.getDouble(25),
                        rs.getDouble(26),rs.getDouble(27),rs.getTimestamp(28),rs.getDouble(29),rs.getDouble(30),
                        rs.getDouble(31),rs.getTimestamp(32),rs.getDouble(33),rs.getDouble(34),rs.getDouble(35),
                        rs.getTimestamp(36),rs.getDouble(37),rs.getDouble(38),rs.getDouble(39),rs.getTimestamp(40),
                        rs.getDouble(41),rs.getDouble(42),rs.getDouble(43),rs.getTimestamp(44),rs.getDouble(45),
                        rs.getDouble(46),rs.getDouble(47),rs.getInt(48),rs.getInt(49),rs.getInt(50),
                        rs.getInt(51),rs.getInt(52),rs.getInt(53),rs.getInt(54),rs.getInt(55),
                        rs.getInt(56),rs.getInt(57),rs.getInt(58),rs.getInt(59),rs.getInt(60),
                        rs.getInt(61),rs.getInt(62),rs.getInt(63),rs.getTimestamp(64),rs.getTimestamp(65)
                        ,rs.getInt(66)));
          }
        } catch (Exception e) {
            SendMailUtil.sendMail("1=="+e.getMessage());
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
        String sql = "replace into t_aarrr_user (id,user_id,user_name,inserttime) VALUES (?,?,?,'2019-10-20');";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Druid连接池获取连接
            conn = DruidManager_TAarrrUser.getInstance().getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < 1000; i++) {
                ps.setInt(1,i+20);
                ps.setString(2,"第"+(i+20)+"行,第2列");
                ps.setString(3,"第"+(i+20)+"行,第3列");
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
