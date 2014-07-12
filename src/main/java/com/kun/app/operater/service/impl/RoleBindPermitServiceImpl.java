/**
 * Program  : RoleBindPermitServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月26日 上午10:23:45
 *
 */

package com.kun.app.operater.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kun.app.operater.data.RoleBindPermitMapper;
import com.kun.app.operater.model.Operater;
import com.kun.app.operater.model.Role;
import com.kun.app.operater.model.RoleBindPermit;
import com.kun.app.operater.service.IRoleBindPermitService;
import com.kun.common.bean.Pagination;
import com.kun.common.data.IMapper;
import com.kun.common.exception.ServiceException;
import com.kun.common.service.impl.AbstractServiceImpl;

/**
 * 角色-权限绑定 业务类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月26日 上午10:23:45
 */
@Service("roleBindPermitService")
public class RoleBindPermitServiceImpl extends AbstractServiceImpl<RoleBindPermit> implements IRoleBindPermitService {

	@Resource(name = "roleBindPermitMapper")
	private RoleBindPermitMapper roleBindPermitMapper;

	@Override
	public void updateBindsByRole(Long roleId, String ids, Operater operater) throws ServiceException {
		// 删除所有已经绑定到该后台用户的记录
		try {
			this.roleBindPermitMapper.deleteByRole(roleId);
			// 新增新的绑定信息
			if (ids != null && ids.length() > 0) {
				String[] idsStr = ids.split(",");
				RoleBindPermit rbp;
				Date date = new Date();
				Role role = new Role();
				role.setId(roleId);
				for (int i = 0; i < idsStr.length; i++) {
					rbp = new RoleBindPermit();
					rbp.setRoleId(roleId);
					rbp.setPermitId(Long.parseLong(idsStr[i]));
					rbp.setCreateTime(date);
					rbp.setUpdateTime(date);
					rbp.setOperaterCode(operater.getCode());
					rbp.setOperaterId(operater.getId());
					rbp.setStatus(1);
					this.save(rbp);
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<RoleBindPermit> listByRole(Long roleId, Pagination page) throws ServiceException {
		try {
			List<RoleBindPermit> list = this.roleBindPermitMapper.listByRole(roleId, page);
			if (list == null || list.size() < page.getPageSize()) {
				page.setTotalRows((page.getPageNumber() - 1) * page.getPageSize() + (list == null ? 0 : list.size()));
			} else {
				page.setTotalRows(this.roleBindPermitMapper.getCountByRole(roleId));
			}
			return list;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public IMapper<RoleBindPermit> getMapper() {
		return this.roleBindPermitMapper;
	}

}
