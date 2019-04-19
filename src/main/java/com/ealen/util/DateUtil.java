package com.ealen.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * G 年代标志符
 * y 年
 * M 月
 * d 日
 * h 时 在上午或下午 (1~12)
 * H 时 在一天中 (0~23)
 * m 分
 * s 秒
 * S 毫秒
 * E 星期
 * D 一年中的第几天
 * F 一月中第几个星期几
 * w 一年中第几个星期
 * W 一月中第几个星期
 * a 上午 / 下午 标记符
 * k 时 在一天中 (1~24)
 * K 时 在上午或下午 (0~11)
 * z 时区
 *
 * 常用时间格式：yyyy-MM-dd HH:mm:ss
 *
 * @author gaochuanjun
 * @since 16/9/21
 */
public class DateUtil {

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    public static String longToString(long currentTime, String formatType) throws ParseException {
        return dateToString(longToDate(currentTime, formatType), formatType);
    }

    public static Date stringToDate(String strTime, String formatType) throws ParseException {
        return new SimpleDateFormat(formatType).parse(strTime);
    }
    
    public static boolean isValid(String timeStr, String formatStr){
        DateFormat formatter = new SimpleDateFormat(formatStr);
        try{
            Date date = (Date)formatter.parse(timeStr);
            return timeStr.equals(formatter.format(date));
        }catch(Exception e){
           return false;
        }
    }
    
    public static boolean isParamDateValid(String date){
        if (!DateUtil.isValid(date, "yyyy-MM-dd'T'HH:mm:ss") && !DateUtil.isValid(date, "yyyy-MM-dd")
                && !DateUtil.isValid(date, "yyyy-MM-dd'T'HH:mm:ss.SSS")
                && !DateUtil.isValid(date, "yyyy-MM-dd HH:mm:ss")
                && !DateUtil.isValid(date, "yyyy-MM-dd HH:mm:ss.SSS")) {
            return false;
        }
        return true;
    }
    
    public static String formatDateString(String timeStr, String formatStr){
        DateFormat formatter = new SimpleDateFormat(formatStr);
        try{
            Date date = (Date)formatter.parse(timeStr);
            return dateToString(date, formatStr);
        }catch(Exception e){
           return timeStr;
        }
    }

    public static Date longToDate(long currentTime, String formatType) throws ParseException {
        return stringToDate(dateToString(new Date(currentTime), formatType), formatType);
    }

    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType);
        return date == null ? 0 : dateToLong(date);
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static String date2String(Date date) {
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'GMT' yyyy", Locale.US);
//            Date date = sdf.parse(USdt);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //System.out.println("fy:"+sdf.format(date));
            return sdf.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date dateMinus(String flag, String dt, int timeSpan) throws ParseException {
        Date tmpDt = stringToDate(dt.toString(), "yyyy-MM-dd HH:mm:ss");
        return dateMinus(flag, tmpDt, timeSpan);
    }

    /**
     * time add
     *
     * @param flag     s-SECOND,m-MINUTE,h-HOUR_OF_DAY,d-DAY_OF_MONTH,w-WEEK_OF_MONTH,m-MONTH,y-YEAR
     * @param dt
     * @param timeSpan
     * @return
     */
    public static Date dateMinus(String flag, Date dt, int timeSpan) {
        SimpleDateFormat fot = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        switch (flag.toLowerCase()) {
//            case "ms":
//                cal.add(Calendar.MILLISECOND, timeSpan);
//                break;
            case "s":
                cal.add(Calendar.SECOND, timeSpan);
                break;
            case "mi":
                cal.add(Calendar.MINUTE, timeSpan);
                break;
            case "h":
                cal.add(Calendar.HOUR_OF_DAY, timeSpan);
                break;
            case "d":
                cal.add(Calendar.DAY_OF_MONTH, timeSpan);
                break;
            case "w":
                cal.add(Calendar.WEEK_OF_MONTH, timeSpan);
                break;
            case "m":
                cal.add(Calendar.MONTH, timeSpan);
                break;
            case "y":
                cal.add(Calendar.YEAR, timeSpan);
                break;
        }
        return cal.getTime();
    }

    /**
     * time addToZero
     *
     * @param flag     s-SECOND,m-MINUTE,h-HOUR_OF_DAY,d-DAY_OF_MONTH,w-WEEK_OF_MONTH,m-MONTH,y-YEAR
     * @param dt
     * @param timeSpan
     * @return
     * @throws ParseException
     */
    public static Date dateMinusZero(String flag, Date dt, int timeSpan) throws ParseException {
    	String date = dateToString(dt,Constants.dateYYYYMMDD)+" 00:00:00";
    	dt = stringToDate(date,Constants.dateYYYYMMDDHHmmss);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        switch (flag.toLowerCase()) {
//            case "ms":
//                cal.add(Calendar.MILLISECOND, timeSpan);
//                break;
            case "s":
                cal.add(Calendar.SECOND, timeSpan);
                break;
            case "mi":
                cal.add(Calendar.MINUTE, timeSpan);
                break;
            case "h":
                cal.add(Calendar.HOUR_OF_DAY, timeSpan);
                break;
            case "d":
                cal.add(Calendar.DAY_OF_MONTH, timeSpan);
                break;
            case "w":
                cal.add(Calendar.WEEK_OF_MONTH, timeSpan);
                break;
            case "m":
                cal.add(Calendar.MONTH, timeSpan);
                break;
            case "y":
                cal.add(Calendar.YEAR, timeSpan);
                break;
        }
        return cal.getTime();
    }
    
    /**
     * floor the day
     *
     * @param start
     * @param end
     * @return
     */
    public static int daysBetween(Date start, Date end) {
        int l = (int) ((start.getTime() - end.getTime()) / (1000 * 3600 * 24));
        return l;
    }

    /**
     * ceil the day
     *
     * @param begin
     * @param end
     * @return
     */
    public static int getIntervalDay(Date begin, Date end) {
        if (begin == null || end == null) return 0;
        float l = (float) (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
        if (l > 0) return (int) Math.ceil(l);
        else return (int) Math.floor(l);
    }
    
    /**
     * 比较两个日期的大小
     * @param first开始日期
     * @param second结束日期
     * @return 1 first > second; -1 first < second; 0 first == second
     */
    public static int compareTwoDay(Date first,Date second){
    	if(first.getTime() - second.getTime() > 0){
    		return 1;
    	}else if(first.getTime() - second.getTime() < 0){
    		return -1;
    	}
    	return 0;
    }
    
    /**
     * 获取传入日期的当前月的最后一天
     * @param date
     * @return "2016-11-03" -> "2016-11-31 23:59:59"
     * @throws ParseException 
     */
    public static Date getDateMonthLastDay(Date date) throws ParseException{
    	String stringDate = dateToString(date,Constants.dateYYYYMMDD)+" 23:59:59";
    	date = stringToDate(stringDate,Constants.dateYYYYMMDDHHmmss);
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.setTime(date);
    	calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
    	return calendar.getTime();
    }
    
    /**
     * 获取传入日期的当前月的第一天
     * @param date
     * @return "2016-11-03" -> "2016-11-01 00:00:00"
     * @throws ParseException 
     */
    public static Date getDateMonthFirstDay(Date date) throws ParseException{
      	String stringDate = dateToString(date,Constants.dateYYYYMMDD) +" 00:00:00";
    	date = stringToDate(stringDate,Constants.dateYYYYMMDDHHmmss);
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.setTime(date);
    	calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE)); 
    	return calendar.getTime();
    }
    
    public static boolean isDate(String date) {
        /**
         * 判断日期格式和范围
         */
        // String rexp =
        // "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(date);
        boolean dateType = mat.matches();
        return dateType;
    }
    
    public static void main(String[] args) throws ParseException{
    	System.out.println(dateToString(getDateMonthLastDay(new Date()),"yyyy-MM-dd HH:mm:ss"));
    	System.out.println(dateToString(getDateMonthFirstDay(new Date()),"yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * Ceiling the days between
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int dateDaysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));
    } 
}
