package com.marsh.zutils.helper;


import com.marsh.zutils.entity.SendSMSParam;

/**
 * 短信通知接口
 */
public interface SMSHelper {

	void sendSMS(SendSMSParam param);
}
