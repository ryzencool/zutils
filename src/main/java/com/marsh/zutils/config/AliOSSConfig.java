package com.marsh.zutils.config;

import com.marsh.zutils.property.AliOSSProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AliOSSProperties.class)
public class AliOSSConfig {
	public static  final String TEMPLATE_URL = "https://%s.oss-%s.aliyuncs.com/%s";
}