package com.marsh.zutils.config;

import com.marsh.zutils.property.TxSMSProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TxSMSProperties.class)
public class SMSConfig {
}
