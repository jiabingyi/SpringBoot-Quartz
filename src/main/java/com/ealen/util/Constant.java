package com.ealen.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 常量
 * 
 * @author zhuyouzhi
 *
 */
public class Constant {
//    @Autowired
//    private Props props;
    // 日期格式
    public static String dateYYYYMMDD = "yyyy-MM-dd";

    public static String dateYYYYMMDDHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static String dateYYYYMMDDTHHmmss = "yyyy-MM-dd'T'HH:mm:ss";

    public static String dateYYYYMMDDHHmmssSSS = "yyyy-MM-dd HH:mm:ss SSS";

    public static String dateYYYYMMDDHHmmsszzz = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String dateYYYYMMDDTHHmmsszzz = "yyyy-MM-dd'T'HH:mm:ss.sss";
    public static String dateyyyymmddhhmmss = "yyyy-MM-dd HH:mm:ss";

    public static String YYYYMMDD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYYMM = "yyyy-MM";
    /**
     * 是否启用userLock缓存
     */
    public static boolean isCash = true;
    /**
     * 用户加锁lock
     */
    public static String userLockCashKey = "userLock";

    public static String ENCODING_GB2312 = "gb2312";
    // redis 默认失效时间(秒)
    // public final static int redisExpire = 30 * 60 * 1000;
    // for performance test
    public final static int redisExpire =30 * 60 * 1000;
    
    public final static int AVOID_REPEATSEND_EXPIRE = 10 * 60 * 1000;

    public static final int NUM_PERTREAD_USERNUM = 5;

    public static final int THREAD_POOL_SIZE = 10;

    public static final String CODE_502_MSG = "{\"code\":\"502\",\"msg\":\"502 Bad Gateway\"}";
    public static final String CODE_404_MSG = "{\"code\":\"404\",\"msg\":\"404 Not found\"}";

    // 用户数据缓存key
    public static String USERDATACASHKEY = "PcBillLastUploadTime";
    // 邮件服务器
//    public static String SMTP = "cs192.168.1.210";
    public static String SMTP = "cssmtp.ppdaicorp.com";
    // 邮件服务器账号
    public static String ACCOUNT = "csdeploy@ppdai.com";
    // 邮件账号
    public static String USERNAME = "csdeploy";
    // 邮件密码
    public static String PASSWORD = "cscndns22!";
    // 接口调用异常接收邮箱
    public static String RECEIVER = "zhangyu09@ppdai.com;dainan@ppdai.com;zhuming@ppdai.com;yurongxing@ppdai.com";

    static {

        if (ReadPropertiesUtil.get("smtp") != null) {
            SMTP = ReadPropertiesUtil.get("smtp");
        }
        if (ReadPropertiesUtil.get("account") != null) {
            ACCOUNT = ReadPropertiesUtil.get("account");
        }
        if (ReadPropertiesUtil.get("userName") != null) {
            USERNAME = ReadPropertiesUtil.get("userName");
        }
        if (ReadPropertiesUtil.get("password") != null) {
            PASSWORD = ReadPropertiesUtil.get("password");
        }

        if (ReadPropertiesUtil.get("receiver") != null) {
            RECEIVER = ReadPropertiesUtil.get("receiver");
        }
    }
    public static String Return0_API_AddBillLogs = "AddBillLogs";
    public static String Return0_API_DeleteBill = "DeleteBill";
    public static String ReturnFalse_API_DeleteBill = "PhoneBillIsExistsInHalfYear";
    public static String Return_Default_List = "[]";

    public static String METRICS_TAG_SOAPWEBSERVICE = "SoapWebservice";

    public static int FOREACH_QUERY = 1000;
    
    public static int FOREACH_HBASE_QUERY = 1000;
    
    // 15021650932,13000001001,13800138000,13010199999,18918910000,13800240110,13800571505,13800591505,13905730000,13777777777
    public static List<String> INVALIDPHONELIST = new ArrayList<String>(
            Arrays.asList("15021650932", "13000001001", "13800138000", "13010199999", "18918910000", "13800240110",
                    "13800571505", "13800591505", "13905730000", "13777777777"));
    
    public static int BILLPHOBNE_BATCHSEND_SIZE = 2000;
    
    public static int USER_FOREACH_QUERY = 50;
    
    public static final String REST_OPTIONS_USERINFO = "userinfo";
    
    public static final String REST_OPTIONS_USERDETAILS = "userdetailsinfo";
    
    public static final String REST_URL_USERSAPI = "http://readapi.user.ppdaicorp.com/queryData";
    
    public static final String REST_BILLCELLPHONE_APPID = "10940001";
    
    public static final String REST_URL_CALLNUMBER_READPCAPI = "http://readpc.ppdapi.com/rest/getTop15CallnumberByUserId";

}
