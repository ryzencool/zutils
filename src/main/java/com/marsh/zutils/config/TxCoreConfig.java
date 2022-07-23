package com.marsh.zutils.config;

import com.marsh.zutils.property.TxCoreProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({TxCoreProperties.class})
@Configuration
public class TxCoreConfig {

	@Bean
	public Credential credential(TxCoreProperties properties) {
		return new Credential(properties.getSecretId(), properties.getSecretKey());
	}

	@Bean
	public SmsClient smsClient(Credential credential, TxCoreProperties properties) {
		ClientProfile clientProfile = new ClientProfile();
		return new SmsClient(credential, properties.getRegionId(), clientProfile);
	}
}
