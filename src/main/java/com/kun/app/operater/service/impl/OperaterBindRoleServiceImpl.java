/**
 * Program  : OperaterBindRoleServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午9:34:58
 *
 */

package com.kun.app.operater.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kun.app.operater.data.OperaterBindRoleMapper;
import com.kun.app.operater.model.Operater;
import com.kun.app.operater.model.OperaterBindRole;
import com.kun.app.operater.service.IOperaterBindRoleService;
import com.kun.common.data.IMapper;
import com.kun.common.exception.ServiceException;
import com.kun.common.service.impl.AbstractServiceImpl;

/**
 * 操作者与角色关联service
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午9:34:58
 */
@Service("operaterBindRoleService")
public class OperaterBindRoleServiceImpl extends AbstractServiceImpl<OperaterBindRole>
		implements
			IOperaterBindRoleService {

	@Resource(name = "operaterBindRoleMapper")
	private OperaterBindRoleMapper operaterBindRoleMapper;

	@Override
	public void updateBindsByOperater(Long userId, String ids, Operater operater) throws ServiceException {
		// 删除所有已经绑定到该后台用户的记录
		this.operaterBindRoleMapper.deleteByOperater(userId);
		// 新增新的绑定信息
		if (ids != null && ids.length() > 0) {
			String[] idsStr = ids.split(",");
			OperaterBindRole obr;
			Date date = new Date();
			for (int i = 0; i < idsStr.length; i++) {
				obr = new OperaterBindRole();
				obr.setOperaterCode(operater.getOperaterCode());
				obr.setOperaterId(operater.getId());
				obr.setUserId(userId);
				obr.setRoleId(Long.parseLong(idsStr[i]));
				obr.setCreateTime(date);
				obr.setUpdateTime(date);
				obr.setStatus(1);
				this.save(obr);
			}
		}
	}

	@Override
	public IMapper<OperaterBindRole> getMapper() {
		// TODO Auto-generated method stub
		return this.operaterBindRoleMapper;
	}

}
