package com.ealen.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

//<dependency>
//<groupId>org.apache.commons</groupId>
//<artifactId>commons-lang3</artifactId>
//<version>3.3.2</version>
//</dependency>
public class MD5Util {
    public static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static final String MD5 = "MD5";
    public static final String SHA = "SHA";
    public static final String UTF8 = "UTF-8";


    public static String digestMD5(Map<String, String> dataMap, String appSecret) {
        return digest(dataMap, appSecret, "MD5");
    }

    public static String digestSHA(Map<String, String> dataMap, String appSecret) {
        return digest(dataMap, appSecret, "SHA");
    }

    private static String digest(Map<String, String> dataMap, String appSecret, String degestType) {
        if(dataMap == null) {
            throw new IllegalArgumentException("数据不能为空");
        } else if(appSecret == null) {
            throw new IllegalArgumentException("安全校验码数据不能为空");
        } else if(degestType == null) {
            throw new IllegalArgumentException("摘要算法不能为空");
        } else {
            TreeMap headMap = new TreeMap(dataMap);
            String str = serialMapToString(appSecret, headMap);

            try {
                return degestType.equals("MD5")? DigestUtils.md5Hex(str.getBytes("UTF-8")): DigestUtils.shaHex(str.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
                throw new RuntimeException("签名失败", var6);
            }
        }
    }

    private static String serialMapToString(String appSecret, Map<String, String> headMap) {
        StringBuffer query = new StringBuffer(appSecret);
        Set paramSet = headMap.entrySet();
        Iterator var5 = paramSet.iterator();

        while(var5.hasNext()) {
            Map.Entry param = (Map.Entry)var5.next();
            query.append((String)param.getKey()).append((String)param.getValue());
        }

        query.append(appSecret);
        return query.toString();
    }
    public static String md5Signature(Map<String, String> params, String secret) {
        Set<String> set = params.keySet();
        String[] keys = new String[set.size()];
        set.toArray(keys);
        Arrays.sort(keys);
        StringBuffer buffer = new StringBuffer();
        for (String key : keys) {
            if ("fields".equalsIgnoreCase(key)) {
                buffer.append("[" + params.get(key) + "]");
            } else {
                buffer.append(params.get(key));
            }
        }
        buffer.append(secret);
        return DigestUtils.md5Hex(buffer.toString());
    }

    public synchronized static final byte[] toMd5(String data, String encodingType) {
        MessageDigest digest = null;
        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException nsae) {
                System.err.println("Failed to load the MD5 MessageDigest. ");
                nsae.printStackTrace();
            }
        }
        if (StringUtils.isBlank(data)) {
            return null;
        }
        try {
            // 最重要的是这句,需要加上编码类型
            digest.update(data.getBytes(encodingType));
        } catch (UnsupportedEncodingException e) {
            digest.update(data.getBytes());
        }
        return digest.digest();
    }

    public static String MD5Encode(String origin, String encodingType) {
        byte[] md5Bytes = toMd5(origin, encodingType);
        return byteArrayToHexString(md5Bytes);
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        char cd1 = hexDigits[d1];
        char cd2 = hexDigits[d2];
        String result = String.valueOf(cd1) +  String.valueOf(cd2);
        return result;
    }

}
