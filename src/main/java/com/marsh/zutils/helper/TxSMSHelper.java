package com.marsh.zutils.helper;

import com.marsh.zutils.entity.SendSMSParam;
import com.marsh.zutils.property.TxSMSProperties;
import com.marsh.zutils.util.StringUtil;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TxSMSHelper implements SMSHelper {

	private final TxSMSProperties txSMSProperties;

	private final SmsClient smsClient;

	public TxSMSHelper(TxSMSProperties txSMSProperties, SmsClient smsClient) {
		this.txSMSProperties = txSMSProperties;
		this.smsClient = smsClient;
	}

	@Override
	public void sendSMS(SendSMSParam param) {
		SendSmsRequest req = new SendSmsRequest();
		req.setSmsSdkAppid(txSMSProperties.getAppId());
		req.setSign(txSMSProperties.getSignName());
		req.setSenderId(null);
		req.setTemplateID(param.getTemplateCode());
		req.setPhoneNumberSet(new String[]{"+86" + param.getPhoneNumber()});
		String[] tplParam = StringUtil.splitStrWithComma(param.getTemplateParam());
		req.setTemplateParamSet(tplParam);
		try {
			smsClient.SendSms(req);
		} catch (TencentCloudSDKException e) {
			log.error("腾讯云发送短信失败，号码：{}, 内容：{}，原因：{}",
					param.getPhoneNumber(),
					param.getTemplateParam(),
					e.getMessage());
		}
	}
}
