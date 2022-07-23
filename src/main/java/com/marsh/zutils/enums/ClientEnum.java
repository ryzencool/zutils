package com.marsh.zutils.enums;

/**
 * 客户端
 */
public enum  ClientEnum {
	/**
	 * 网页
	 */
	WEB(1),

	/**
	 * 安卓
	 */
	ANDROID(2),

	/**
	 * ios
	 */
	IOS(3),

	/**
	 * 微信小程序
	 */
	WX_MINI_APP(4);

	private final int type;

	ClientEnum(int type) {
		this.type = type;
	}
}
