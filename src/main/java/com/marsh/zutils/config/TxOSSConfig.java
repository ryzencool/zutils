package com.marsh.zutils.config;

import com.marsh.zutils.property.TxOSSProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(TxOSSProperties.class)
public class TxOSSConfig {



	public static final String TEMPLATE_URL = "https://%s.cos.%s.myqcloud.com/%s";

}