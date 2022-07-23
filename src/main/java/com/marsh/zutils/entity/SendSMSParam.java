package com.marsh.zutils.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class SendSMSParam {

	private String phoneNumber;

	private String signName;

	private String templateCode;

	/**
	 * 短信模板内容
	 * 1.阿里云，json串
	 * 2.腾讯云，'，'间隔的数组
	 */
	private String templateParam;

	/**
	 * 腾讯云专用
	 */
	private String appId;

}
