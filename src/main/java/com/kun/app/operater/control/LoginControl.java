package com.kun.app.operater.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kun.app.operater.model.Operater;
import com.kun.app.operater.service.IOperaterService;
import com.kun.common.constant.Constants;
import com.kun.common.exception.ServiceException;
import com.kun.common.web.control.BaseControl;
import com.kun.common.web.response.MessageOut;
import com.kun.common.web.response.Out;

@Controller("loginControl")
@RequestMapping("/login")
public class LoginControl extends BaseControl<Operater> {

	@Resource(name = "operaterService")
	private IOperaterService operaterService;

	/**
	 * 登录
	 * 
	 * @author songkun
	 * @return String
	 * @date 2011-6-1 下午12:48:45
	 * @since 2.0.0
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public Out<Object> login(Operater operater) {
		try {
			Operater tmp = this.operaterService.validate(operater);
			if (tmp == null) {// 登陆失败
				return MessageOut.LOGIN_FAIL_MESSAGE;
			}
			((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true)
					.setAttribute(Constants.USER_INFO, tmp);
			return MessageOut.LOGIN_OK_MESSAGE;
		} catch (ServiceException e) {
			this.getLogger().error(e);
		} catch (Exception e) {
			this.getLogger().error(e);
		}
		return MessageOut.LOGIN_FAIL_MESSAGE;
	}

	/**
	 * 退出
	 * 
	 * @author songkun
	 * @return String
	 * @date 2011-6-1 下午12:49:55
	 * @since 2.0.0
	 */
	@RequestMapping("/logout.do")
	@ResponseBody
	public Out<Object> logout() {
		try {
			((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession()
					.removeAttribute(Constants.USER_INFO);
		} catch (Exception e) {
			this.getLogger().error(e);
		}
		return MessageOut.LOGOUT_OK_MESSAGE;
	}
}
