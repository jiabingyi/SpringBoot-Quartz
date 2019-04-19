package com.ealen.util;

public class Constants {

    public static final String CODE_502_MSG = "{\"code\":\"502\",\"msg\":\"502 Bad Gateway\"}";
    
    public static final String CODE_404_MSG = "{\"code\":\"404\",\"msg\":\"404 Not found\"}";
    
    
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

    //freshGetTotalResult 5 * 60 * 1000
    public static long redisExpire = 5 * 60 * 1000;
    public static long reserveMills = 2 * 60 * 1000;
    public static long activeMills = redisExpire - reserveMills;
    //100  1000
    public static int PAGESIZE = 2;
    public static int MOBILEHISTORYPAGESIZE = 3000;
    
    // json content page size. 5000
    public static Integer CONTENT_PAGE_SIZE = 5000;
    
    
}
