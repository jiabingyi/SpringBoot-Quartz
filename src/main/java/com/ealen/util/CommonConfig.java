package com.ealen.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonConfig {

//    @Value("#{common.isRecordFilter}")
//    private String isRecordFilter;
    @Value("#{common.isCacheOpen}")
    private String isCacheOpen;
    @Value("#{common.isSendMail}")
    private String isSendMail;
    @Value("#{common.top10orderstr}")
    private String top10orderstr;
//    @Value("#{common.isWriteBillCallPhoneLog}")
//    private String isWriteBillCallPhoneLog;
    public String getIsCacheOpen() {
        return isCacheOpen;
    }

    public void setIsCacheOpen(String isCacheOpen) {
        this.isCacheOpen = isCacheOpen;
    }

//    public String getIsRecordFilter() {
//        return isRecordFilter;
//    }
//
//    public void setIsRecordFilter(String isRecordFilter) {
//        this.isRecordFilter = isRecordFilter;
//    }

	public String getIsSendMail() {
		return isSendMail;
	}

	public void setIsSendMail(String isSendMail) {
		this.isSendMail = isSendMail;
	}
//    public String getIsWriteBillCallPhoneLog() {
//        return isWriteBillCallPhoneLog;
//    }
//
//    public void setIsWriteBillCallPhoneLog(String isWriteBillCallPhoneLog) {
//        this.isWriteBillCallPhoneLog = isWriteBillCallPhoneLog;
//    }

    public String getTop10orderstr() {
        return top10orderstr;
    }

    public void setTop10orderstr(String top10orderstr) {
        this.top10orderstr = top10orderstr;
    }
}
