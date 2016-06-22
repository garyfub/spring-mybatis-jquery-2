/**
 * Program  : Location.java
 * Author   : songkun
 * Create   : 2015年12月18日 上午11:40:05
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

package com.kun.common.bean;

/**
 * 长方形
 *
 * @author songkun
 * @version 1.0.0
 * @date 2015年12月18日 上午11:40:05
 */
public class Rectangle {

	private double top;// 上
	private double bottom;// 下
	private double left;// 左
	private double right;// 右

	public Rectangle(double top, double bottom, double left, double right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public double getBottom() {
		return bottom;
	}
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Rectangle [top=" + top + ", bottom=" + bottom + ", left=" + left + ", right=" + right + "]";
	}

}
