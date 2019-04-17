package com.ealen.sendMail.mailutil_ppd;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 通用工具类
 * 
 * @author zhuyouzhi
 *
 */
public class CommonUtil {

	public static String GetIPaddress(){
        String ip = "";
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			ip = inetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return ip;
    }
	
	public static void main(String[] args){
		System.out.println(GetIPaddress());
	}
}
