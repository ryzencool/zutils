package com.marsh.zutils.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ali.oss")
public class AliOSSProperties {

	private String endpoint;

	private String accessKeyId;

	private String accessKeySecret;

	private String region;



}
