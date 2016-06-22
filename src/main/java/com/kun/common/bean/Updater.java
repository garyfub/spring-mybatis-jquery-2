/**
 * Program  : Updater.java
 * Author   : songkun
 * Create   : 2016年1月11日 下午3:57:15
 *
 * Copyright 2016 songkun. All rights reserved.
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
 * app更新
 *
 * @author songkun
 * @version 1.0.0
 * @date 2016年1月11日 下午3:57:15
 */
public class Updater {

	private boolean hasNewer = false;
	private String packUrl;

	public boolean isHasNewer() {
		return hasNewer;
	}
	public void setHasNewer(boolean hasNewer) {
		this.hasNewer = hasNewer;
	}
	public String getPackUrl() {
		return packUrl;
	}
	public void setPackUrl(String packUrl) {
		this.packUrl = packUrl;
	}

}
