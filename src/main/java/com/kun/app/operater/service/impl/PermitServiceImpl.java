/**
 * Program  : PermitServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月25日 下午2:01:44
 *
 */

package com.kun.app.operater.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kun.app.operater.data.PermitMapper;
import com.kun.app.operater.model.Permit;
import com.kun.app.operater.service.IPermitService;
import com.kun.common.data.IMapper;
import com.kun.common.exception.ServiceException;
import com.kun.common.service.impl.AbstractServiceImpl;

/**
 * 权限业务处理实现类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月25日 下午2:01:44
 */
@Service("permitService")
public class PermitServiceImpl extends AbstractServiceImpl<Permit> implements IPermitService {

	@Resource(name = "permitMapper")
	private PermitMapper permitMapper;

	@Override
	public List<Permit> listPermitsByRole(Serializable key) throws ServiceException {
		try {
			return this.permitMapper.listPermitsByRole(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public List<Permit> listUnbindPermitsByRole(Serializable key) throws ServiceException {
		try {
			return this.permitMapper.listUnbindPermitsByRole(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Permit> listPermitByOperater(Serializable key) throws ServiceException {
		try {
			return this.permitMapper.listPermitsByOperater(key);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public IMapper<Permit> getMapper() {
		// TODO Auto-generated method stub
		return this.permitMapper;
	}

}
