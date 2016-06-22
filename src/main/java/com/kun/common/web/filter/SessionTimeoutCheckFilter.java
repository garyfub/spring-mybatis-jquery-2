package com.kun.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.kun.Context;

/**
 * Session过期检查.
 * 
 */
public class SessionTimeoutCheckFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(SessionTimeoutCheckFilter.class);

	private FilterConfig filterConfig;
	private String[] vips;

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String tmp = filterConfig.getInitParameter("vips");
		if (tmp != null && tmp.length() > 0) {
			vips = tmp.split(",");
		}
	}

	public void setFilterConfig(FilterConfig filterconfig) {
		this.filterConfig = filterconfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	/**
	 * 判断url是否需要做超时验证
	 * 
	 * @author songkun
	 * @create 2014年7月3日 上午9:41:57
	 * @since
	 * @param url
	 * @return
	 */
	private boolean isVIP(String url) {
		if (vips == null || vips.length <= 0 || url == null || url.length() <= 0) {
			return false;
		}
		for (int i = 0; i < vips.length; i++) {
			if (url.indexOf(vips[i]) > -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 超时重定向
	 * 
	 * @author songkun
	 * @create 2014年7月3日 上午10:28:50
	 * @since
	 * @param request
	 * @param response
	 */
	private boolean redirect(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.setContentType("text/html; charset=utf-8");
				response.setStatus(408);
				response.getWriter().write(request.getContextPath() + "/");
				return true;
			} else if (request.getServletPath() != null && request.getServletPath().indexOf("main.html") >= 0) {
				response.sendRedirect(request.getContextPath() + "/");
				return true;
			}
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return false;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			String requestUri = request.getServletPath();
			if (!this.isVIP(requestUri)) {
				HttpServletResponse response = (HttpServletResponse) res;
				HttpSession session = request.getSession();
				if ((session == null || session.getAttribute(Context.USER_INFO) == null)) {
					if (redirect(request, response)) {
						return;
					}
				}
			}
			chain.doFilter(req, res);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}
}
