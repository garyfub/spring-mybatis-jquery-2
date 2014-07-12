/**
 * Program  : RoleServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午9:12:29
 *
 */

package com.kun.app.operater.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kun.app.operater.data.RoleBindPermitMapper;
import com.kun.app.operater.data.RoleMapper;
import com.kun.app.operater.model.Role;
import com.kun.app.operater.service.IRoleService;
import com.kun.common.bean.Pagination;
import com.kun.common.data.IMapper;
import com.kun.common.exception.ServiceException;
import com.kun.common.service.impl.AbstractServiceImpl;

/**
 * 角色业务实现类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午9:12:29
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractServiceImpl<Role> implements IRoleService {

	@Resource(name = "roleBindPermitMapper")
	private RoleBindPermitMapper roleBindPermitMapper;
	@Resource(name = "roleMapper")
	private RoleMapper roleMapper;

	@Override
	public List<Role> listByOperater(Serializable key, Pagination page) throws ServiceException {
		try {
			return this.roleMapper.listByOperater(key, page);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Role> listUnbindRolesByOperater(Serializable key) throws ServiceException {
		try {
			return this.roleMapper.listUnbindRolesByOperater(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isExist(Role role) throws ServiceException {
		Role tmp = new Role();
		tmp.setName(role.getName());
		tmp = (Role) findOneByExample(tmp);
		return (tmp != null) && !(tmp.getId().equals(role.getId()));
	}

	@Override
	public void deleteByKey(Serializable key) throws ServiceException {
		try {
			// 笨拙的级联删除
			this.roleBindPermitMapper.deleteByRole(key);
			super.deleteByKey(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public IMapper<Role> getMapper() {
		return this.roleMapper;
	}

}
