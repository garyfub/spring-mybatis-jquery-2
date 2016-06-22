/**
 * Program  : QrCodeServlet.java
 * Author   : songkun
 * Create   : 2016年4月18日 下午12:59:54
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

package com.kun.common.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个只是个样例，可以删除，也可以修改成对应的servlet
 *
 * @author songkun
 * @version 1.0.0
 * @date 2016年4月18日 下午12:59:54
 */
public class TempServlet extends HttpServlet {

	private static final long serialVersionUID = -6086285323789613518L;

	/**
	 * 处理get/post请求
	 * 
	 * @author songkun
	 * @create 2016年4月18日 下午3:02:34
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
