package com.marsh.zutils.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tx.oss")
public class TxOSSProperties {

	private String secretId;

	private String secretKey;

	private String region;

	private String bucketName;

}
