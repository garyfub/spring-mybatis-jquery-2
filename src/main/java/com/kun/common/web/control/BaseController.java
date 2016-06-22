/**
 * Program  : BaseControl.java
 * Author   : songkun
 * Create   : 2014年6月28日 上午9:51:14
 *
 */

package com.kun.common.web.control;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kun.Context;
import com.kun.app.operater.model.Operater;

/**
 * 控制层基类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年6月28日 上午9:51:14
 */
public abstract class BaseController<T> {

	/**
	 * 获取当前登录的用户信息
	 * 
	 * @author songkun
	 * @create 2014年6月28日 上午11:02:11
	 * @return Operater
	 */
	protected Operater getCurrentOperater() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		if (request.getSession() == null) {
			return null;
		}
		return (Operater) request.getSession().getAttribute(Context.USER_INFO);
	}

	/**
	 * 获取服务器上dir的绝对路径
	 * 
	 * @author songkun
	 * @create 2016年1月7日 下午5:43:22
	 * @param fileName
	 * @return String
	 */
	protected String getRealPath(String dir) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getSession().getServletContext().getRealPath(dir);
	}

	/**
	 * 获取basePath
	 * 
	 * @author songkun
	 * @create 2016年1月7日 下午6:21:50
	 * @return String
	 */
	protected String getBasePath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		if (basePath.endsWith("/")) {
			return basePath.substring(0, basePath.length() - 1);
		}
		return basePath;
	}

	/**
	 * 获取Logger
	 * 
	 * @author songkun
	 * @return
	 * @return Logger
	 * @date 2011-5-30 下午08:18:01
	 * @since 2.0.0
	 */
	protected final Logger getLogger() {
		return Logger.getLogger(this.getClass());
	}
}
