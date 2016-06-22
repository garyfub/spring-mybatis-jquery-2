/**
 * Program  : OperaterServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月24日 下午11:37:01
 *
 */

package com.kun.app.operater.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	@Override
	public Operater validate(Operater operater) throws ServiceException {
		try {
			Operater temp = new Operater();
			operater.setName(operater.getName());
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
			Operater tmp = this.operaterMapper.findOneByExample(operater);
			return (tmp != null) && !(tmp.getId().equals(operater.getId()));
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
