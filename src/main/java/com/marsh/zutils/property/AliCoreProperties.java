package com.marsh.zutils.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ali.core")
public class AliCoreProperties {

	private String regionId;

	private String accessKeyId;

	private String accessKeySecret;
}
