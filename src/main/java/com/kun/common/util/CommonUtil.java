/**
 * Program  : CommonUtil.java
 * Author   : songkun
 * Create   : 2015年12月18日 上午11:39:16
 *
 * Copyright 2015 songkun. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of songkun.  
 * You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement 
 * you entered into with songkun.
 *
 */

package com.kun.common.util;

/**
 * 通用工具包
 *
 * @author songkun
 * @version 1.0.0
 * @date 2015年12月18日 上午11:39:16
 */
public class CommonUtil {

	private final static double PI = 3.14159265358979323; // 圆周率
	private final static double R = 6371229; // 地球的半径

	/**
	 * 计算距离
	 * 
	 * @author songkun
	 * @create 2015年12月6日 下午7:30:52
	 * @param longt1
	 * @param lat1
	 * @param longt2
	 * @param lat2
	 * @return double
	 */
	public static final double getDistance(double longt1, double lat1, double longt2, double lat2) {
		if (longt1 == longt2 && lat1 == lat2) {
			return 0;
		}
		double x, y, distance;
		x = (longt2 - longt1) * PI * R * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
		y = (lat2 - lat1) * PI * R / 180;
		distance = Math.hypot(x, y);
		return distance;
	}

	/**
	 * 判断字符串是否是纯int数字,去掉起始、结尾空格
	 * 
	 * @param num
	 * @return boolean
	 */
	public static final boolean isNumber(String num) {
		if (num == null || num.length() <= 0) {
			return false;
		}
		num = num.trim();
		char c = 0;
		for (int i = 0; i < num.length(); i++) {
			c = num.charAt(i);
			if ((c > '9' || c < '0') && c != '.') {
				return false;
			}
		}
		return true;
	}

}
