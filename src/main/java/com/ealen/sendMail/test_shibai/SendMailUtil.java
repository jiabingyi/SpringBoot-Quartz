package com.ealen.sendMail.test_shibai;


import java.io.File;

/**
 * Created by Administrator on 2017/4/10.
 */

public class SendMailUtil {


    public static void main(String[] args) {
        send("jiayuxiang@ppdai.com");

    }



    //sohu
    private static final String HOST = "smtp.sohu.com";
    private static final String PORT = "465";//
    private static final String FROM_ADD = "jia1308352196@163.com";
    private static final String FROM_PSW = "Jia@12344";
    //163
//    private static final String HOST = "smtp.163.com";
//    private static final String PORT = "25";//465/994   25
//    private static final String FROM_ADD = "jia1308352196@163.com";
//    private static final String FROM_PSW = "Jia@12344";

//    private static final String HOST = "smtp.qq.com";
//    private static final String PORT = "587";
//    private static final String FROM_ADD = "1739204916@qq.com";
//    private static final String FROM_PSW = "jiabingyi123457";

//    //163
//    private static final String HOST = "smtp.163.com";
//    private static final String PORT = "465"; //或者465  994
//    private static final String FROM_ADD = "teprinciple@163.com";
//    private static final String FROM_PSW = "teprinciple163";
////    private static final String TO_ADD = "2584770373@qq.com";


    public static void send(final File file,String toAdd){
        final MailInfo mailInfo = creatMail(toAdd);
        final MailSender sms = new MailSender();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                sms.sendFileMail(mailInfo,file);
            }
        }).start();
    }


    public static void send(String toAdd){
        final MailInfo mailInfo = creatMail(toAdd);
        final MailSender sms = new MailSender();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                sms.sendTextMail(mailInfo);
            }
        }).start();
    }

    private static MailInfo creatMail(String toAdd) {
        final MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost(HOST);
        mailInfo.setMailServerPort(PORT);
        mailInfo.setValidate(true);
        mailInfo.setUserName(FROM_ADD); // 你的邮箱用户名
        mailInfo.setPassword(FROM_PSW);// 您的邮箱密码
        mailInfo.setFromAddress(FROM_ADD); // 发送的邮箱
        mailInfo.setToAddress(toAdd); // 发到哪个邮件去
        mailInfo.setSubject("Hello"); // 邮件主题
        mailInfo.setContent("Android 测试"); // 邮件文本
        return mailInfo;
    }

}