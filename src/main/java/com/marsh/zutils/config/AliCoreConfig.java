package com.marsh.zutils.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.marsh.zutils.property.AliCoreProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AliCoreProperties.class})
public class AliCoreConfig {

	@Bean
	public IAcsClient iAcsClient(AliCoreProperties aliCoreProperties) {
		DefaultProfile profile = DefaultProfile.getProfile(aliCoreProperties.getRegionId(), aliCoreProperties.getAccessKeyId(), aliCoreProperties.getAccessKeySecret());
		return new DefaultAcsClient(profile);
	}



}
