/**
 * Program  : OperaterServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月24日 下午11:37:01
 *
 */

package com.kun.app.operater.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kun.app.operater.data.OperaterBindRoleMapper;
import com.kun.app.operater.data.OperaterMapper;
import com.kun.app.operater.model.Operater;
import com.kun.app.operater.service.IOperaterService;
import com.kun.common.data.IMapper;
import com.kun.common.exception.ServiceException;
import com.kun.common.service.impl.AbstractServiceImpl;
import com.kun.common.util.MD5Util;

/**
 * 操作员业务处理实现类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月24日 下午11:37:01
 */
@Service("operaterService")
public class OperaterServiceImpl extends AbstractServiceImpl<Operater> implements IOperaterService {

	@Resource(name = "operaterMapper")
	private OperaterMapper operaterMapper;

	@Resource(name = "operaterBindRoleMapper")
	private OperaterBindRoleMapper operaterBindRoleMapper;

	@Override
	public Operater validate(Operater operater) throws ServiceException {
		try {
			Operater temp = new Operater();
			temp.setCode(operater.getCode());
			temp.setStatus(1);
			temp = (Operater) findOneByExample(temp);
			if (temp == null || !temp.getPassword().equals(MD5Util.getMD5String(operater.getPassword())))
				return null;
			return temp;
		} catch (ServiceException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isExist(Operater operater) throws ServiceException {
		try {
			Operater tmp = this.operaterMapper.getOneByNameOrCode(operater);
			return (tmp != null) && !(tmp.getId().equals(operater.getId()));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Operater operater) throws ServiceException {
		try {
			// 级联删除.这种方式有点别扭，不如hibernate来得美观。没有找到仅仅通过配置xml来达到级联删除的方式
			// 别扭，只是从代码美化、简化角度来说。级联删除的效果是实现了，因为，service方法处于一个事务中
			this.operaterBindRoleMapper.deleteByOperater(operater.getId());
			super.delete(operater);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public IMapper<Operater> getMapper() {
		// TODO Auto-generated method stub
		return this.operaterMapper;
	}
}
