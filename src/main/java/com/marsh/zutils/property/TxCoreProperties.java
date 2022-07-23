package com.marsh.zutils.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tx.core")
public class TxCoreProperties {

	private String secretId;

	private String secretKey;

	private String regionId;
}
