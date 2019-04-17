package com.ealen.entity;

import java.sql.Timestamp;
import java.sql.Timestamp;

public class TAarrrUser {

    private Long   id  ;
     private String  user_id ;
     private String  user_name ;
     private Timestamp register_time;
     private Integer   source_id ;
     private String   source_name ;
     private String  channel_type ;
     private String   channel_type2;
     private String  channel_name ;
     private Integer  channel_id ;

     private Timestamp   auth_time;
     private Integer  auth_count;
     private Integer  auth_count7;
     private Integer  auth_count30;

     private Timestamp   cardbind_time;
     private Integer cardbind_count ;
     private Integer  cardbind_count7;
     private Integer  cardbind_count30;
     private Timestamp   recharge_time;
     private double  recharge_amount;
     private double  recharge_amount7;
     private double  recharge_amount30;
     private Timestamp   invest_time;
     private double  first_invest_amount;
     private double  invest_amount;
     private double invest_amount7 ;
     private double invest_amount30 ;
     private Timestamp   invest_bulk_time;
     private double invest_bulk_amount ;
     private double  invest_bulk_amount7;
     private double invest_bulk_amount30 ;
     private Timestamp invest_rainbow_time  ;
     private double invest_rainbow_amount ;
     private double  invest_rainbow_amount7;
     private double invest_rainbow_amount30 ;
     private Timestamp   invest_rainbowplus_time;
     private double invest_rainbowplus_amount ;
     private double invest_rainbowplus_amount7 ;
     private double invest_rainbowplus_amount30 ;
     private Timestamp   invest_monthshare_time;
     private double invest_monthshare_amount ;
     private double invest_monthshare_amount7 ;
     private double  invest_monthshare_amount30;
     private Timestamp invest_debt_time  ;
     private double invest_debt_amount ;
     private double invest_debt_amount7 ;
     private double invest_debt_amount30 ;

     private Integer register_fail_reqcnt ;

     private Integer auth_fail_reqcnt ;
     private Integer  auth_fail_reqcnt7;
     private Integer auth_fail_reqcnt30 ;

     private Integer  cardbind_succ_reqcnt;
     private Integer  cardbind_succ_reqcnt7;
     private Integer cardbind_succ_reqcnt30 ;
     private Integer  cardbind_fail_reqcnt;
     private Integer  cardbind_fail_reqcnt7;
     private Integer  cardbind_fail_reqcnt30;

     private Integer  recharge_fail_reqcnt;
     private Integer  recharge_fail_reqcnt7;
     private Integer  recharge_fail_reqcnt30;
     private Integer  recharge_succ_reqcnt;
     private Integer recharge_succ_reqcnt7 ;
     private Integer  recharge_succ_reqcnt30;

     private Timestamp inserttime ;
     private Timestamp  updatetime;
     private Integer  isactive ;

    public TAarrrUser(Long id, String user_id, String user_name, Timestamp register_time, Integer source_id, String source_name, String channel_type, String channel_type2, String channel_name, Integer channel_id, Timestamp auth_time, Integer auth_count, Integer auth_count7, Integer auth_count30, Timestamp cardbind_time, Integer cardbind_count, Integer cardbind_count7, Integer cardbind_count30, Timestamp recharge_time, double recharge_amount, double recharge_amount7, double recharge_amount30, Timestamp invest_time, double first_invest_amount, double invest_amount, double invest_amount7, double invest_amount30, Timestamp invest_bulk_time, double invest_bulk_amount, double invest_bulk_amount7, double invest_bulk_amount30, Timestamp invest_rainbow_time, double invest_rainbow_amount, double invest_rainbow_amount7, double invest_rainbow_amount30, Timestamp invest_rainbowplus_time, double invest_rainbowplus_amount, double invest_rainbowplus_amount7, double invest_rainbowplus_amount30, Timestamp invest_monthshare_time, double invest_monthshare_amount, double invest_monthshare_amount7, double invest_monthshare_amount30, Timestamp invest_debt_time, double invest_debt_amount, double invest_debt_amount7, double invest_debt_amount30, Integer register_fail_reqcnt, Integer auth_fail_reqcnt, Integer auth_fail_reqcnt7, Integer auth_fail_reqcnt30, Integer cardbind_succ_reqcnt, Integer cardbind_succ_reqcnt7, Integer cardbind_succ_reqcnt30, Integer cardbind_fail_reqcnt, Integer cardbind_fail_reqcnt7, Integer cardbind_fail_reqcnt30, Integer recharge_fail_reqcnt, Integer recharge_fail_reqcnt7, Integer recharge_fail_reqcnt30, Integer recharge_succ_reqcnt, Integer recharge_succ_reqcnt7, Integer recharge_succ_reqcnt30, Timestamp inserttime, Timestamp updatetime, Integer isactive) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.register_time = register_time;
        this.source_id = source_id;
        this.source_name = source_name;
        this.channel_type = channel_type;
        this.channel_type2 = channel_type2;
        this.channel_name = channel_name;
        this.channel_id = channel_id;
        this.auth_time = auth_time;
        this.auth_count = auth_count;
        this.auth_count7 = auth_count7;
        this.auth_count30 = auth_count30;
        this.cardbind_time = cardbind_time;
        this.cardbind_count = cardbind_count;
        this.cardbind_count7 = cardbind_count7;
        this.cardbind_count30 = cardbind_count30;
        this.recharge_time = recharge_time;
        this.recharge_amount = recharge_amount;
        this.recharge_amount7 = recharge_amount7;
        this.recharge_amount30 = recharge_amount30;
        this.invest_time = invest_time;
        this.first_invest_amount = first_invest_amount;
        this.invest_amount = invest_amount;
        this.invest_amount7 = invest_amount7;
        this.invest_amount30 = invest_amount30;
        this.invest_bulk_time = invest_bulk_time;
        this.invest_bulk_amount = invest_bulk_amount;
        this.invest_bulk_amount7 = invest_bulk_amount7;
        this.invest_bulk_amount30 = invest_bulk_amount30;
        this.invest_rainbow_time = invest_rainbow_time;
        this.invest_rainbow_amount = invest_rainbow_amount;
        this.invest_rainbow_amount7 = invest_rainbow_amount7;
        this.invest_rainbow_amount30 = invest_rainbow_amount30;
        this.invest_rainbowplus_time = invest_rainbowplus_time;
        this.invest_rainbowplus_amount = invest_rainbowplus_amount;
        this.invest_rainbowplus_amount7 = invest_rainbowplus_amount7;
        this.invest_rainbowplus_amount30 = invest_rainbowplus_amount30;
        this.invest_monthshare_time = invest_monthshare_time;
        this.invest_monthshare_amount = invest_monthshare_amount;
        this.invest_monthshare_amount7 = invest_monthshare_amount7;
        this.invest_monthshare_amount30 = invest_monthshare_amount30;
        this.invest_debt_time = invest_debt_time;
        this.invest_debt_amount = invest_debt_amount;
        this.invest_debt_amount7 = invest_debt_amount7;
        this.invest_debt_amount30 = invest_debt_amount30;
        this.register_fail_reqcnt = register_fail_reqcnt;
        this.auth_fail_reqcnt = auth_fail_reqcnt;
        this.auth_fail_reqcnt7 = auth_fail_reqcnt7;
        this.auth_fail_reqcnt30 = auth_fail_reqcnt30;
        this.cardbind_succ_reqcnt = cardbind_succ_reqcnt;
        this.cardbind_succ_reqcnt7 = cardbind_succ_reqcnt7;
        this.cardbind_succ_reqcnt30 = cardbind_succ_reqcnt30;
        this.cardbind_fail_reqcnt = cardbind_fail_reqcnt;
        this.cardbind_fail_reqcnt7 = cardbind_fail_reqcnt7;
        this.cardbind_fail_reqcnt30 = cardbind_fail_reqcnt30;
        this.recharge_fail_reqcnt = recharge_fail_reqcnt;
        this.recharge_fail_reqcnt7 = recharge_fail_reqcnt7;
        this.recharge_fail_reqcnt30 = recharge_fail_reqcnt30;
        this.recharge_succ_reqcnt = recharge_succ_reqcnt;
        this.recharge_succ_reqcnt7 = recharge_succ_reqcnt7;
        this.recharge_succ_reqcnt30 = recharge_succ_reqcnt30;
        this.inserttime = inserttime;
        this.updatetime = updatetime;
        this.isactive = isactive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }

    public Integer getSource_id() {
        return source_id;
    }





    public void setSource_id(Integer source_id) {
        this.source_id = source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public String getChannel_type2() {
        return channel_type2;
    }

    public void setChannel_type2(String channel_type2) {
        this.channel_type2 = channel_type2;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public Integer getChannel_id() {
        return channel_id;
    }





    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public Timestamp getAuth_time() {
        return auth_time;
    }

    public void setAuth_time(Timestamp auth_time) {
        this.auth_time = auth_time;
    }

    public Integer getAuth_count() {
        return auth_count;
    }

    public void setAuth_count(Integer auth_count) {
        this.auth_count = auth_count;
    }

    public Integer getAuth_count7() {
        return auth_count7;
    }

    public void setAuth_count7(Integer auth_count7) {
        this.auth_count7 = auth_count7;
    }

    public Integer getAuth_count30() {
        return auth_count30;
    }

    public void setAuth_count30(Integer auth_count30) {
        this.auth_count30 = auth_count30;
    }

    public Timestamp getCardbind_time() {
        return cardbind_time;
    }





    public void setCardbind_time(Timestamp cardbind_time) {
        this.cardbind_time = cardbind_time;
    }

    public Integer getCardbind_count() {
        return cardbind_count;
    }

    public void setCardbind_count(Integer cardbind_count) {
        this.cardbind_count = cardbind_count;
    }

    public Integer getCardbind_count7() {
        return cardbind_count7;
    }

    public void setCardbind_count7(Integer cardbind_count7) {
        this.cardbind_count7 = cardbind_count7;
    }

    public Integer getCardbind_count30() {
        return cardbind_count30;
    }

    public void setCardbind_count30(Integer cardbind_count30) {
        this.cardbind_count30 = cardbind_count30;
    }

    public Timestamp getRecharge_time() {
        return recharge_time;
    }

    public void setRecharge_time(Timestamp recharge_time) {
        this.recharge_time = recharge_time;
    }

    public double getRecharge_amount() {
        return recharge_amount;
    }




    public void setRecharge_amount(double recharge_amount) {
        this.recharge_amount = recharge_amount;
    }

    public double getRecharge_amount7() {
        return recharge_amount7;
    }

    public void setRecharge_amount7(double recharge_amount7) {
        this.recharge_amount7 = recharge_amount7;
    }

    public double getRecharge_amount30() {
        return recharge_amount30;
    }

    public void setRecharge_amount30(double recharge_amount30) {
        this.recharge_amount30 = recharge_amount30;
    }

    public Timestamp getInvest_time() {
        return invest_time;
    }

    public void setInvest_time(Timestamp invest_time) {
        this.invest_time = invest_time;
    }

    public double getFirst_invest_amount() {
        return first_invest_amount;
    }

    public void setFirst_invest_amount(double first_invest_amount) {
        this.first_invest_amount = first_invest_amount;
    }

    public double getInvest_amount() {
        return invest_amount;
    }




    public void setInvest_amount(double invest_amount) {
        this.invest_amount = invest_amount;
    }

    public double getInvest_amount7() {
        return invest_amount7;
    }

    public void setInvest_amount7(double invest_amount7) {
        this.invest_amount7 = invest_amount7;
    }

    public double getInvest_amount30() {
        return invest_amount30;
    }

    public void setInvest_amount30(double invest_amount30) {
        this.invest_amount30 = invest_amount30;
    }

    public Timestamp getInvest_bulk_time() {
        return invest_bulk_time;
    }

    public void setInvest_bulk_time(Timestamp invest_bulk_time) {
        this.invest_bulk_time = invest_bulk_time;
    }

    public double getInvest_bulk_amount() {
        return invest_bulk_amount;
    }

    public void setInvest_bulk_amount(double invest_bulk_amount) {
        this.invest_bulk_amount = invest_bulk_amount;
    }

    public double getInvest_bulk_amount7() {
        return invest_bulk_amount7;
    }





    public void setInvest_bulk_amount7(double invest_bulk_amount7) {
        this.invest_bulk_amount7 = invest_bulk_amount7;
    }

    public double getInvest_bulk_amount30() {
        return invest_bulk_amount30;
    }

    public void setInvest_bulk_amount30(double invest_bulk_amount30) {
        this.invest_bulk_amount30 = invest_bulk_amount30;
    }

    public Timestamp getInvest_rainbow_time() {
        return invest_rainbow_time;
    }

    public void setInvest_rainbow_time(Timestamp invest_rainbow_time) {
        this.invest_rainbow_time = invest_rainbow_time;
    }

    public double getInvest_rainbow_amount() {
        return invest_rainbow_amount;
    }

    public void setInvest_rainbow_amount(double invest_rainbow_amount) {
        this.invest_rainbow_amount = invest_rainbow_amount;
    }

    public double getInvest_rainbow_amount7() {
        return invest_rainbow_amount7;
    }

    public void setInvest_rainbow_amount7(double invest_rainbow_amount7) {
        this.invest_rainbow_amount7 = invest_rainbow_amount7;
    }

    public double getInvest_rainbow_amount30() {
        return invest_rainbow_amount30;
    }





    public void setInvest_rainbow_amount30(double invest_rainbow_amount30) {
        this.invest_rainbow_amount30 = invest_rainbow_amount30;
    }

    public Timestamp getInvest_rainbowplus_time() {
        return invest_rainbowplus_time;
    }

    public void setInvest_rainbowplus_time(Timestamp invest_rainbowplus_time) {
        this.invest_rainbowplus_time = invest_rainbowplus_time;
    }

    public double getInvest_rainbowplus_amount() {
        return invest_rainbowplus_amount;
    }

    public void setInvest_rainbowplus_amount(double invest_rainbowplus_amount) {
        this.invest_rainbowplus_amount = invest_rainbowplus_amount;
    }

    public double getInvest_rainbowplus_amount7() {
        return invest_rainbowplus_amount7;
    }

    public void setInvest_rainbowplus_amount7(double invest_rainbowplus_amount7) {
        this.invest_rainbowplus_amount7 = invest_rainbowplus_amount7;
    }

    public double getInvest_rainbowplus_amount30() {
        return invest_rainbowplus_amount30;
    }

    public void setInvest_rainbowplus_amount30(double invest_rainbowplus_amount30) {
        this.invest_rainbowplus_amount30 = invest_rainbowplus_amount30;
    }

    public Timestamp getInvest_monthshare_time() {
        return invest_monthshare_time;
    }





    public void setInvest_monthshare_time(Timestamp invest_monthshare_time) {
        this.invest_monthshare_time = invest_monthshare_time;
    }

    public double getInvest_monthshare_amount() {
        return invest_monthshare_amount;
    }

    public void setInvest_monthshare_amount(double invest_monthshare_amount) {
        this.invest_monthshare_amount = invest_monthshare_amount;
    }

    public double getInvest_monthshare_amount7() {
        return invest_monthshare_amount7;
    }

    public void setInvest_monthshare_amount7(double invest_monthshare_amount7) {
        this.invest_monthshare_amount7 = invest_monthshare_amount7;
    }

    public double getInvest_monthshare_amount30() {
        return invest_monthshare_amount30;
    }

    public void setInvest_monthshare_amount30(double invest_monthshare_amount30) {
        this.invest_monthshare_amount30 = invest_monthshare_amount30;
    }

    public Timestamp getInvest_debt_time() {
        return invest_debt_time;
    }

    public void setInvest_debt_time(Timestamp invest_debt_time) {
        this.invest_debt_time = invest_debt_time;
    }

    public double getInvest_debt_amount() {
        return invest_debt_amount;
    }






    public void setInvest_debt_amount(double invest_debt_amount) {
        this.invest_debt_amount = invest_debt_amount;
    }

    public double getInvest_debt_amount7() {
        return invest_debt_amount7;
    }

    public void setInvest_debt_amount7(double invest_debt_amount7) {
        this.invest_debt_amount7 = invest_debt_amount7;
    }

    public double getInvest_debt_amount30() {
        return invest_debt_amount30;
    }

    public void setInvest_debt_amount30(double invest_debt_amount30) {
        this.invest_debt_amount30 = invest_debt_amount30;
    }

    public Integer getRegister_fail_reqcnt() {
        return register_fail_reqcnt;
    }

    public void setRegister_fail_reqcnt(Integer register_fail_reqcnt) {
        this.register_fail_reqcnt = register_fail_reqcnt;
    }

    public Integer getAuth_fail_reqcnt() {
        return auth_fail_reqcnt;
    }

    public void setAuth_fail_reqcnt(Integer auth_fail_reqcnt) {
        this.auth_fail_reqcnt = auth_fail_reqcnt;
    }

    public Integer getAuth_fail_reqcnt7() {
        return auth_fail_reqcnt7;
    }






    public void setAuth_fail_reqcnt7(Integer auth_fail_reqcnt7) {
        this.auth_fail_reqcnt7 = auth_fail_reqcnt7;
    }

    public Integer getAuth_fail_reqcnt30() {
        return auth_fail_reqcnt30;
    }

    public void setAuth_fail_reqcnt30(Integer auth_fail_reqcnt30) {
        this.auth_fail_reqcnt30 = auth_fail_reqcnt30;
    }

    public Integer getCardbind_succ_reqcnt() {
        return cardbind_succ_reqcnt;
    }

    public void setCardbind_succ_reqcnt(Integer cardbind_succ_reqcnt) {
        this.cardbind_succ_reqcnt = cardbind_succ_reqcnt;
    }

    public Integer getCardbind_succ_reqcnt7() {
        return cardbind_succ_reqcnt7;
    }

    public void setCardbind_succ_reqcnt7(Integer cardbind_succ_reqcnt7) {
        this.cardbind_succ_reqcnt7 = cardbind_succ_reqcnt7;
    }

    public Integer getCardbind_succ_reqcnt30() {
        return cardbind_succ_reqcnt30;
    }

    public void setCardbind_succ_reqcnt30(Integer cardbind_succ_reqcnt30) {
        this.cardbind_succ_reqcnt30 = cardbind_succ_reqcnt30;
    }

    public Integer getCardbind_fail_reqcnt() {
        return cardbind_fail_reqcnt;
    }






    public void setCardbind_fail_reqcnt(Integer cardbind_fail_reqcnt) {
        this.cardbind_fail_reqcnt = cardbind_fail_reqcnt;
    }

    public Integer getCardbind_fail_reqcnt7() {
        return cardbind_fail_reqcnt7;
    }

    public void setCardbind_fail_reqcnt7(Integer cardbind_fail_reqcnt7) {
        this.cardbind_fail_reqcnt7 = cardbind_fail_reqcnt7;
    }

    public Integer getCardbind_fail_reqcnt30() {
        return cardbind_fail_reqcnt30;
    }

    public void setCardbind_fail_reqcnt30(Integer cardbind_fail_reqcnt30) {
        this.cardbind_fail_reqcnt30 = cardbind_fail_reqcnt30;
    }

    public Integer getRecharge_fail_reqcnt() {
        return recharge_fail_reqcnt;
    }

    public void setRecharge_fail_reqcnt(Integer recharge_fail_reqcnt) {
        this.recharge_fail_reqcnt = recharge_fail_reqcnt;
    }

    public Integer getRecharge_fail_reqcnt7() {
        return recharge_fail_reqcnt7;
    }

    public void setRecharge_fail_reqcnt7(Integer recharge_fail_reqcnt7) {
        this.recharge_fail_reqcnt7 = recharge_fail_reqcnt7;
    }

    public Integer getRecharge_fail_reqcnt30() {
        return recharge_fail_reqcnt30;
    }





    public void setRecharge_fail_reqcnt30(Integer recharge_fail_reqcnt30) {
        this.recharge_fail_reqcnt30 = recharge_fail_reqcnt30;
    }

    public Integer getRecharge_succ_reqcnt() {
        return recharge_succ_reqcnt;
    }

    public void setRecharge_succ_reqcnt(Integer recharge_succ_reqcnt) {
        this.recharge_succ_reqcnt = recharge_succ_reqcnt;
    }

    public Integer getRecharge_succ_reqcnt7() {
        return recharge_succ_reqcnt7;
    }

    public void setRecharge_succ_reqcnt7(Integer recharge_succ_reqcnt7) {
        this.recharge_succ_reqcnt7 = recharge_succ_reqcnt7;
    }

    public Integer getRecharge_succ_reqcnt30() {
        return recharge_succ_reqcnt30;
    }

    public void setRecharge_succ_reqcnt30(Integer recharge_succ_reqcnt30) {
        this.recharge_succ_reqcnt30 = recharge_succ_reqcnt30;
    }

    public Timestamp getInserttime() {
        return inserttime;
    }

    public void setInserttime(Timestamp inserttime) {
        this.inserttime = inserttime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }
}
