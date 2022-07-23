package com.marsh.zutils.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 多状态工具类，待优化，暂用
 */
public class MStatusUtil {

	public static List<Integer> scatter(int total) {
		switch (total) {
			case 1:
				return Lists.newArrayList(1);
			case 2:
				return Lists.newArrayList(2);
			case 3:
				return Lists.newArrayList(1, 2);
			case 4:
				return Lists.newArrayList(4);
			case 5:
				return Lists.newArrayList(1, 4);
			case 6:
				return Lists.newArrayList(2, 4);
			case 7:
				return Lists.newArrayList(1, 2, 4);
			default:
				return Lists.newArrayList(1);
		}
	}


}
