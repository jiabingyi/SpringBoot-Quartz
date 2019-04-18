package com.ealen.datasource_TAarrrUser;


import com.ealen.datasource_TAarrrUser.druid_from.From;
import com.ealen.datasource_TAarrrUser.druid_to.To;
import com.ealen.entity.Config;
import com.ealen.entity.TAarrrUser;
import com.ealen.sendMail.mailutil_ppd.SendMailUtil;

import java.text.SimpleDateFormat;
import java.util.List;

public class FromAndTo {

    public static void main(String[] args) {

        fromAndTo(2, "2019-10-20", "2019-10-20");

    }

    public static void fromAndTo(int rows, String startDate, String endDate) {
        int first = 0;
        try {
            while (true) {

                List<TAarrrUser> list = From.queryByRowNum(first, rows, startDate, endDate);
                To.insertRow(list);

                first = first + rows;
                if (list.size() < rows) {
                    first= first - rows+ list.size();
                    break;}
            }
        } catch (Exception e) {
//            SendMailUtil.sendMail("今日导数数据量：" + first + "条数据。"+"\n"+e.getMessage());
//            System.out.println(e.getMessage());
//            Config.USER=e.getMessage();
            SendMailUtil.sendMail("3=="+e.getMessage());
            e.printStackTrace();
        } finally {
            SendMailUtil.sendMail("今日导数数据量：" + first + "条数据。");
        }
    }


}
