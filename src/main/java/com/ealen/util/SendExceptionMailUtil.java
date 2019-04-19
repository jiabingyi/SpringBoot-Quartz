package com.ealen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 异常邮件发送工具类
 * @author jiangzhengquan
 *
 */
public class SendExceptionMailUtil {
	private static final Logger logger = LoggerFactory.getLogger(SendExceptionMailUtil.class);
	private static CommonConfig commonConfig = SpringContextHolder.getBean(CommonConfig.class);
	public static void sendMail(String interfaceName, Exception e) {
		if(!"1".equals(commonConfig.getIsSendMail())){
			return;
		}
		String ip = CommonUtil.GetIPaddress();
		String subject ="[newAppBillSearch]["+ ip + "]" + interfaceName + "接口异常";
		String body = ExceptionUtils.getFullStackTrace(e);
		boolean sendMail = AssemblyMail.sendAndCc(Constant.SMTP,
				Constant.ACCOUNT, Constant.RECEIVER, "", subject, body,
				Constant.USERNAME, Constant.PASSWORD, false, null);

		if (!sendMail) {
			logger.info("邮件发送失败");
		}
	}
}
