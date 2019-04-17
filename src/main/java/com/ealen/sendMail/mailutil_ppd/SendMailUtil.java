package com.ealen.sendMail.mailutil_ppd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 异常邮件发送工具类
 *
 * @author jiangzhengquan
 */
public class SendMailUtil {
    private static final Logger logger = LoggerFactory.getLogger(SendMailUtil.class);

    public static void main(String[] args) {
        sendMail("jiayuxiang_test");
    }


    public static void sendMail(String body) {


        String ip = CommonUtil.GetIPaddress();
        String subject = "[][" + ip + "]" + "海盗模型mysql结果数据备份到历史表中";
        boolean sendMail = AssemblyMail.sendAndCc(Constant.SMTP,
                Constant.ACCOUNT, Constant.RECEIVER, "", subject, body,
                Constant.USERNAME, Constant.PASSWORD, false, null);

        if (!sendMail) {
//            System.out.println("邮件发送失败");
        }
    }

    public static void sendMail(String body, String head) {

        boolean sendMail = AssemblyMail.sendAndCc(Constant.SMTP,
                Constant.ACCOUNT, Constant.RECEIVER, "", head, body,
                Constant.USERNAME, Constant.PASSWORD, false, null);

        if (!sendMail) {
//            System.out.println("邮件发送失败");
        }
    }
}
