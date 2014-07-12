/**
 * Program  : OperaterBindRoleAction.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午9:18:04
 *
 */

package com.kun.app.operater.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kun.app.operater.model.OperaterBindRole;
import com.kun.app.operater.model.Role;
import com.kun.app.operater.service.IOperaterBindRoleService;
import com.kun.app.operater.service.IRoleService;
import com.kun.common.bean.Pagination;
import com.kun.common.exception.ServiceException;
import com.kun.common.web.control.BaseControl;
import com.kun.common.web.response.DataOut;
import com.kun.common.web.response.MessageOut;
import com.kun.common.web.response.Out;

/**
 * 操作员-角色 绑定关系（关联表可以不要control/service）
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午9:18:04
 */
@Controller("operaterBindRoleControl")
@RequestMapping("/operaterBindRole")
public class OperaterBindRoleControl extends BaseControl<OperaterBindRole> {

	@Resource(name = "operaterBindRoleService")
	private IOperaterBindRoleService operaterBindRoleService;
	@Resource(name = "roleService")
	private IRoleService roleService;

	/**
	 * 获取未绑定的角色列表
	 * 
	 * @author songkun
	 * @return
	 * @return String
	 * @date 2011-6-20 下午08:02:37
	 * @since 2.0.0
	 */
	@RequestMapping("/listUnbindRole.do")
	@ResponseBody
	public Out<Role> listUnbindRole(OperaterBindRole obr) {
		try {
			return new DataOut<Role>(this.roleService.listUnbindRolesByOperater(obr.getUserId()), null);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取绑定到某用户的角色列表
	 * 
	 * @author songkun
	 * @return
	 * @return String
	 * @date 2011-6-20 下午08:03:45
	 * @since 2.0.0
	 */
	@RequestMapping("/listBindedRole.do")
	@ResponseBody
	public Out<Role> listBindedRole(OperaterBindRole obr, Pagination pagination) {
		try {
			return new DataOut<Role>(this.roleService.listByOperater(obr.getUserId(), pagination), pagination);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改绑定
	 * 
	 * @author songkun
	 * @return Out
	 * @date 2011-6-20 下午08:09:18
	 * @since 2.0.0
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Out<Object> update(OperaterBindRole obr, String ids) {
		try {
			this.operaterBindRoleService.updateBindsByOperater(obr.getUserId(), ids, this.getCurrentOperater());
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException ex) {
			ex.printStackTrace();
			getLogger().error(ex.getMessage(), ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().error(ex.getMessage(), ex);
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;

	}
}
