/**
 * Program  : WebServiceImpl.java
 * Author   : songkun
 * Create   : 2014年4月19日 下午12:04:12
 *
 */

package com.kun.webservice.server.impl;

import javax.jws.WebService;

import com.kun.app.operater.model.Operater;
import com.kun.app.operater.service.IOperaterService;
import com.kun.common.exception.ServiceException;
import com.kun.webservice.server.IWebService;

/**
 * webservice 具体实现类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月19日 下午12:04:12
 */
@WebService(endpointInterface = "com.kun.webservice.server.IWebService")
public class WebServiceImpl implements IWebService {

	private IOperaterService operaterService;

	@Override
	public boolean login(String userCode, String pswd) {
		Operater operater = new Operater();
		operater.setCode(userCode);
		operater.setPassword(pswd);
		try {
			if (operaterService.validate(operater) != null) {
				return true;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String sayHello(String name) {
		// TODO Auto-generated method stub
		return "Hello~ " + name;
	}

	public IOperaterService getOperaterService() {
		return operaterService;
	}

	public void setOperaterService(IOperaterService operaterService) {
		this.operaterService = operaterService;
	}

}
