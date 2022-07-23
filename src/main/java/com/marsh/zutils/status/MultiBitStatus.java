package com.marsh.zutils.status;


import lombok.Getter;
import lombok.Setter;

public class MultiBitStatus {

	@Getter
	@Setter
	protected int flag;

	public void setStatus(int status) {
		flag = status;
	}

	/**
	 * 添加一项或多项权限
	 */
	public MultiBitStatus enable(int status) {
		flag |= status;
		return this;
	}

	/**
	 * 删除一项或多项权限
	 */
	public MultiBitStatus disable(int status) {
		flag &= ~status;
		return this;
	}

	/**
	 * 是否拥某些权限
	 */
	public boolean isAllow(int status) {
		return (flag & status) == status;
	}

	/**
	 * 是否禁用了某些权限
	 */
	public boolean isNotAllow(int status) {
		return (flag & status) == 0;
	}

	/**
	 * 是否仅仅拥有某些权限
	 */
	public boolean isOnlyAllow(int status) {
		return flag == status;
	}

}
