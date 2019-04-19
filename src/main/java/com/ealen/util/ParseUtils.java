package com.ealen.util;

/**
 * @author lizhedao
 * @since 3/24/17
 */
public class ParseUtils {
    public static int tryParseInt(String s,int defaultValue){
        try {
            return Integer.parseInt(s);
        }catch (Exception ex){
            return defaultValue;
        }
    }
}
