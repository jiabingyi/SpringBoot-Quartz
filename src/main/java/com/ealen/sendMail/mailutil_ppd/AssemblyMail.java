package com.ealen.sendMail.mailutil_ppd;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;
import java.util.Map.Entry;

/**
 * mail的组装
 *
 *
 */
public class AssemblyMail {

    private MimeMessage mimeMsg; // MIME邮件对象
    private Session session; // 邮件会话对象
    private Properties props; // 系统属性
    // smtp认证用户名和密码
    private String username;
    private String password;
    private MimeMultipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

    /**
     * Constructor
     *
     * @param smtp
     *            邮件发送服务器
     */
    public AssemblyMail(String smtp) {
        setSmtpHost(smtp);
        createMimeMessage();
    }

    /**
     * 设置邮件发送服务器
     *
     * @param hostName
     *            String
     */
    public void setSmtpHost(String hostName) {
//        System.out.println("设置系统属性：mail.smtp.host = " + hostName);
        if (props == null)
            props = System.getProperties(); // 获得系统属性对象
        props.put("mail.smtp.host", hostName); // 设置SMTP主机
    }

    /**
     * 创建MIME邮件对象
     *
     * @return
     */
    public boolean createMimeMessage() {
        try {
//            System.out.println("准备获取邮件会话对象！");
            session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
        } catch (Exception e) {
//            System.out.println("获取邮件会话对象时发生错误！" + e.getMessage());
            return false;
        }

//        System.out.println("准备创建MIME邮件对象！");
        try {
            mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
            mp = new MimeMultipart();

            return true;
        } catch (Exception e) {
//            System.out.println("创建MIME邮件对象失败！" + e.getMessage());
            return false;
        }
    }

    /**
     * 设置SMTP是否需要验证
     *
     * @param need
     */
    public void setNeedAuth(boolean need) {
//        System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
        if (props == null)
            props = System.getProperties();
        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
    }

    /**
     * 设置用户名和密码
     *
     * @param name
     * @param pass
     */
    public void setNamePass(String name, String pass) {
        username = name;
        password = pass;
    }

    /**
     * 设置邮件主题
     *
     * @param mailSubject
     * @return
     */
    public boolean setSubject(String mailSubject) {
//        System.out.println("设置邮件主题！");
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
//            System.out.println("设置邮件主题发生错误！");
            return false;
        }
    }

    /**
     * 设置邮件正文
     *
     * @param mailBody
     *            String
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent("" + mailBody, "text/html;charset=UTF-8");
            bp.addHeader("Content-Transfer-Encoding", "base64");
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            System.out.println("设置邮件正文时发生错误！" + e.getMessage());
            return false;
        }
    }

    /**
     * 添加附件
     *
     * @param filePath
     *            默认附件路径
     * @param fileName
     *            自定义附件名称
     */
    public boolean addFileAffix(String filePath, String fileName) {

//        System.out.println("增加邮件附件：" + filePath);
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filePath);
            bp.setDataHandler(new DataHandler(fileds));
            if (StringUtils.isEmpty(fileName)) {
                bp.setFileName(fileds.getName());
            } else {
                String fileNameEnc = "=?UTF-8?B?" + Base64.encodeBase64String(fileName.getBytes("UTF-8")) + "?=";
                fileNameEnc = fileNameEnc.replaceAll("\r", "").replaceAll("\n", "");
                bp.setFileName(fileNameEnc);
            }
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
//            System.out.println("增加邮件附件：" + filePath + "发生错误！" + e.getMessage());
            return false;
        }
    }

    /**
     * 添加嵌入图片
     *
     * @param map
     *            一个有规则的map，用作嵌入图片
     * @return
     */
    public boolean addPicFile(Map<String, String> map) {
        try {
            if (map != null && map.size() > 0) {
                Set<Entry<String, String>> set = map.entrySet();
                for (Iterator iterator = set.iterator(); iterator.hasNext();) {
                    Entry<String, String> entry = (Entry<String, String>) iterator.next();

                    // 创建用于保存图片的MimeBodyPart对象，并将它保存到MimeMultipart中
                    MimeBodyPart gifBodyPart = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(entry.getValue());// 图片所在的目录的绝对路径

                    gifBodyPart.setDataHandler(new DataHandler(fds));
                    gifBodyPart.setContentID("<" + entry.getKey() + ">"); // cid的值
                    gifBodyPart.setHeader("Content-Type", "image/png");
                    mp.addBodyPart(gifBodyPart);
                }
                mp.setSubType("related");
            }
            return true;
        } catch (Exception e) {
//            System.out.println("增加嵌入图片发生错误！" + e.getMessage());
            return false;
        }
    }

    /**
     * 设置发信人
     *
     * @param from
     *            String
     */
    public boolean setFrom(String from) {
//        System.out.println("设置发信人！");
        try {
            mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设置收信人
     *
     * @param to
     *            String
     */
    public boolean setTo(String to) {
        if (to == null)
            return false;
        try {
            List list = new ArrayList();// 不能使用string类型的类型，这样只能发送一个收件人
            String[] median = to.split(";");// 对输入的多个邮件进行分号分割
            for (int i = 0; i < median.length; i++) {
                list.add(new InternetAddress(median[i]));
            }
            InternetAddress[] address = (InternetAddress[]) list.toArray(new InternetAddress[list.size()]);
            mimeMsg.setRecipients(Message.RecipientType.TO, address);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 发送邮件
     */
    public boolean sendOut() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
//            System.out.println("正在发送邮件....");

            Session mailSession = Session.getInstance(props, null);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String) props.get("mail.smtp.host"), username, password);
            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));

//            System.out.println("发送邮件成功！");
            transport.close();
            return true;
        } catch (Exception e) {
//            System.out.println("邮件发送失败！" + e.getMessage());
            return false;
        }
    }

    /**
     * 调用sendOut方法完成邮件发送
     *
     * @param smtp
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param username
     * @param password
     * @return boolean
     */
    public static boolean send(String smtp, String from, String to, String subject, String content, String username,
                               String password) {
        AssemblyMail theMail = new AssemblyMail(smtp);
        theMail.setNeedAuth(true); // 需要验证

        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);

        if (!theMail.sendOut())
            return false;
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带抄送
     *
     * @param smtp
     * @param from
     * @param to
     * @param copyto
     * @param subject
     * @param content
     * @param username
     * @param password
     * @return boolean
     */
    public static boolean sendAndCc(String smtp, String from, String to, String copyto, String subject, String content,
                                    String username, String password, boolean needCss, String mailUID) {
        AssemblyMail theMail = new AssemblyMail(smtp);
        theMail.setNeedAuth(true); // 需要验证

        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);

        if (!theMail.sendOut())
            return false;
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带抄送、内嵌图片
     *
     * @param smtp
     * @param from
     * @param to
     * @param copyto
     * @param subject
     * @param content
     * @param username
     * @param password
     * @param picMap
     *
     * @return boolean
     */
    public static boolean sendAndCc(String smtp, String from, String to, String copyto, String subject, String content,
                                    String username, String password, Map<String, String> picMap, boolean needCss, String mailUID) {
        AssemblyMail theMail = new AssemblyMail(smtp);
        theMail.setNeedAuth(true); // 需要验证

        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.addPicFile(picMap))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);

        if (!theMail.sendOut())
            return false;
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带附件
     *
     * @param smtp
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param username
     * @param password
     * @param filePath
     * @param fileName
     *            附件路径
     * @return
     */
    public static boolean send(String smtp, String from, String to, String subject, String content, String username,
                               String password, String filePath, String fileName) {
        AssemblyMail theMail = new AssemblyMail(smtp);
        theMail.setNeedAuth(true); // 需要验证

        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.addFileAffix(filePath, fileName))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);

        if (!theMail.sendOut())
            return false;
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带附件和抄送
     *
     * @param smtp
     * @param from
     * @param to
     * @param copyto
     * @param subject
     * @param content
     * @param username
     * @param password
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean sendAndCc(String smtp, String from, String to, String copyto, String subject, String content,
                                    String username, String password, String filePath, String fileName, boolean needCss, String mailUID) {
        AssemblyMail theMail = new AssemblyMail(smtp);

        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.addFileAffix(filePath, fileName))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);

        if (!theMail.sendOut())
            return false;
        return true;
    }

    /**
     * 调用sendOut方法完成邮件发送,带附件和抄送,内嵌图片
     *
     * @param smtp
     * @param from
     * @param to
     * @param copyto
     * @param subject
     * @param content
     * @param username
     * @param password
     * @param filePath
     * @param fileName
     * @param picMap
     * @return
     */
    public static boolean sendAndCc(String smtp, String from, String to, String copyto, String subject, String content,
                                    String username, String password, String filePath, String fileName, Map<String, String> picMap,
                                    boolean needCss, String mailUID) {
        AssemblyMail theMail = new AssemblyMail(smtp);

        if (!theMail.setSubject(subject))
            return false;
        if (!theMail.setBody(content))
            return false;
        if (!theMail.addFileAffix(filePath, fileName))
            return false;
        if (!theMail.addPicFile(picMap))
            return false;
        if (!theMail.setTo(to))
            return false;
        if (!theMail.setFrom(from))
            return false;
        theMail.setNamePass(username, password);

        if (!theMail.sendOut())
            return false;

        return true;
    }

    public static void main(String[] args) {
        // "mail.corp.vipshop.com",
        // 发件人 : san.zhang
        // 发件地址 : san.zhang@contoso.com,
        // 发送主机 : 192.168.46.32
        AssemblyMail.sendAndCc("192.168.46.32", "san.zhang@contoso.com",
                "shirley.liu@vipshop.com;stone.zhu@vipshop.com", "", "test_shibai", "testCon", "san.zhang", "abc.1234",
                "E:\\V3\\xls\\95f4636d-2bb7-4c47-a1e1-369640899621.xlsx", "my attachment", true, "");
    }
}
