package com.ealen.datasource_TAarrrUser.druid_to;

import com.ealen.datasource_TAarrrUser.DruidManager_TAarrrUser;
import com.ealen.entity.TAarrrUser;
import com.ealen.sendMail.mailutil_ppd.SendMailUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class To {

    public static  void  insertRow(List<TAarrrUser> list) {
//        String sql = "insert into Table2 (c1,c2,c3) VALUES (?,?,?);";
        String sql = "replace into t_aarrr_user_history (id,user_id,user_name,register_time,source_id,source_name,channel_type,channel_type2,channel_name,channel_id,auth_time,auth_count,auth_count7,auth_count30,cardbind_time,\n" +
                "cardbind_count,cardbind_count7,cardbind_count30,recharge_time,recharge_amount,recharge_amount7,recharge_amount30,invest_time,first_invest_amount,invest_amount,invest_amount7,invest_amount30,invest_bulk_time,\n" +
                "invest_bulk_amount,invest_bulk_amount7,invest_bulk_amount30,invest_rainbow_time,invest_rainbow_amount,invest_rainbow_amount7,invest_rainbow_amount30,invest_rainbowplus_time,invest_rainbowplus_amount,invest_rainbowplus_amount7,invest_rainbowplus_amount30,invest_monthshare_time,invest_monthshare_amount,invest_monthshare_amount7,invest_monthshare_amount30,invest_debt_time,invest_debt_amount,invest_debt_amount7,invest_debt_amount30,\n" +
                "register_fail_reqcnt,auth_fail_reqcnt,auth_fail_reqcnt7,auth_fail_reqcnt30,cardbind_succ_reqcnt,cardbind_succ_reqcnt7,cardbind_succ_reqcnt30,cardbind_fail_reqcnt,cardbind_fail_reqcnt7,cardbind_fail_reqcnt30,\n" +
                "recharge_fail_reqcnt,recharge_fail_reqcnt7,recharge_fail_reqcnt30,recharge_succ_reqcnt,recharge_succ_reqcnt7,recharge_succ_reqcnt30,inserttime,updatetime,isactive)" +
                " VALUES (" +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?,?," +
                "?" +
                ");";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Druid连接池获取连接
            conn = DruidManager_TAarrrUser.getInstance().getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < list.size(); i++) {
                ps.setLong(1,list.get(i).getId());ps.setString(2,list.get(i).getUser_id());ps.setString(3,list.get(i).getUser_name());ps.setTimestamp(4,list.get(i).getRegister_time());ps.setInt(5,list.get(i).getSource_id());
                ps.setString(6,list.get(i).getSource_name());ps.setString(7,list.get(i).getChannel_type());ps.setString(8,list.get(i).getChannel_type2());ps.setString(9,list.get(i).getChannel_name());ps.setInt(10,list.get(i).getChannel_id());
                ps.setTimestamp(11,list.get(i).getAuth_time());ps.setInt(12,list.get(i).getAuth_count());ps.setInt(13,list.get(i).getAuth_count7());ps.setInt(14,list.get(i).getAuth_count30());ps.setTimestamp(15,list.get(i).getCardbind_time());
                ps.setInt(16,list.get(i).getCardbind_count());ps.setInt(17,list.get(i).getCardbind_count7());ps.setInt(18,list.get(i).getCardbind_count30());ps.setTimestamp(19,list.get(i).getRecharge_time());ps.setDouble(20,list.get(i).getRecharge_amount());
                ps.setDouble(21,list.get(i).getRecharge_amount7());ps.setDouble(22,list.get(i).getRecharge_amount30());ps.setTimestamp(23,list.get(i).getInvest_time());ps.setDouble(24,list.get(i).getFirst_invest_amount());ps.setDouble(25,list.get(i).getInvest_amount());
                ps.setDouble(26,list.get(i).getInvest_amount7());ps.setDouble(27,list.get(i).getInvest_amount30());ps.setTimestamp(28,list.get(i).getInvest_bulk_time());ps.setDouble(29,list.get(i).getInvest_bulk_amount());ps.setDouble(30,list.get(i).getInvest_bulk_amount7());
                ps.setDouble(31,list.get(i).getInvest_bulk_amount30());ps.setTimestamp(32,list.get(i).getInvest_rainbow_time());ps.setDouble(33,list.get(i).getInvest_rainbow_amount());ps.setDouble(34,list.get(i).getInvest_rainbow_amount7());ps.setDouble(35,list.get(i).getInvest_rainbow_amount30());
                ps.setTimestamp(36,list.get(i).getInvest_rainbowplus_time());ps.setDouble(37,list.get(i).getInvest_rainbowplus_amount());ps.setDouble(38,list.get(i).getInvest_rainbowplus_amount7());ps.setDouble(39,list.get(i).getInvest_rainbowplus_amount30());ps.setTimestamp(40,list.get(i).getInvest_monthshare_time());
                ps.setDouble(41,list.get(i).getInvest_monthshare_amount());ps.setDouble(42,list.get(i).getInvest_monthshare_amount7());ps.setDouble(43,list.get(i).getInvest_monthshare_amount30());ps.setTimestamp(44,list.get(i).getInvest_debt_time());ps.setDouble(45,list.get(i).getInvest_debt_amount());
                ps.setDouble(46,list.get(i).getInvest_debt_amount7());ps.setDouble(47,list.get(i).getInvest_debt_amount30());ps.setInt(48,list.get(i).getRegister_fail_reqcnt());ps.setInt(49,list.get(i).getRegister_fail_reqcnt());ps.setInt(50,list.get(i).getAuth_fail_reqcnt7());
                ps.setInt(51,list.get(i).getAuth_fail_reqcnt30());ps.setInt(52,list.get(i).getCardbind_succ_reqcnt());ps.setInt(53,list.get(i).getCardbind_succ_reqcnt7());ps.setInt(54,list.get(i).getCardbind_succ_reqcnt30());ps.setInt(55,list.get(i).getCardbind_fail_reqcnt());
                ps.setInt(56,list.get(i).getCardbind_fail_reqcnt7());ps.setInt(57,list.get(i).getCardbind_fail_reqcnt30());ps.setInt(58,list.get(i).getRecharge_fail_reqcnt());ps.setInt(59,list.get(i).getRecharge_fail_reqcnt7());ps.setInt(60,list.get(i).getRecharge_fail_reqcnt30());
                ps.setInt(61,list.get(i).getRecharge_succ_reqcnt());ps.setInt(62,list.get(i).getRecharge_succ_reqcnt7());ps.setInt(63,list.get(i).getRecharge_succ_reqcnt30());ps.setTimestamp(64,list.get(i).getInserttime());ps.setTimestamp(65,list.get(i).getUpdatetime());
                ps.setInt(66,list.get(i).getIsactive());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (Exception e) {
            SendMailUtil.sendMail("2=="+e.getMessage());
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
