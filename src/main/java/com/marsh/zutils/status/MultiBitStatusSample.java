package com.marsh.zutils.status;

public class MultiBitStatusSample extends MultiBitStatus{

	// 是否允许查询，二进制第1位，0表示否，1表示是
	public static final int ALLOW_SELECT = 1 << 0; // 0001

	// 是否允许新增，二进制第2位，0表示否，1表示是
	public static final int ALLOW_INSERT = 1 << 1; // 0010

	// 是否允许修改，二进制第3位，0表示否，1表示是
	public static final int ALLOW_UPDATE = 1 << 2; // 0100

	// 是否允许删除，二进制第4位，0表示否，1表示是
	public static final int ALLOW_DELETE = 1 << 3; // 1000


	public static void main(String[] args) {
		MultiBitStatusSample mul = new MultiBitStatusSample();
		mul.enable(ALLOW_SELECT);
		MultiBitStatusSample mul1 = new MultiBitStatusSample();
		mul1.setFlag(2);

	}

}
