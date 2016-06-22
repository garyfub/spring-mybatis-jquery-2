/**
 * Program  : OperaterAction.java
 * Author   : songkun
 * Create   : 2014年4月24日 下午11:32:42
 *
 */

package com.kun.app.operater.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kun.Context;
import com.kun.app.operater.model.Operater;
import com.kun.app.operater.service.IOperaterService;
import com.kun.common.bean.Pagination;
import com.kun.common.exception.ServiceException;
import com.kun.common.util.MD5Util;
import com.kun.common.web.control.BaseController;
import com.kun.common.web.response.DataOut;
import com.kun.common.web.response.MessageOut;
import com.kun.common.web.response.Out;

/**
 * 后台操作者action
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年4月24日 下午11:32:42
 */
@Controller("operaterControl")
@RequestMapping("/operater")
public class OperaterController extends BaseController<Operater> {

	@Resource(name = "operaterService")
	private IOperaterService operaterService;

	/**
	 * 获取后台用户列表
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param pagination
	 * @return Out
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public Out<Operater> list(Pagination pagination) {
		try {
			return new DataOut<Operater>(this.operaterService.loadOnePage(pagination), pagination);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @return Out
	 */
	@RequestMapping("/add.do")
	@ResponseBody
	public Out<Object> add(Operater operater) {
		if (operater.getName() == null) {
			return MessageOut.ADD_FAIL_MESSAGE;
		}
		try {
			if (this.operaterService.isExist(operater)) {// 如果后台用户已经存在
				return MessageOut.NAME_OR_CODE_EXIST_MESSAGE;
			} else {
				this.getLogger().info("新增用户: " + operater.getName());
				operater.setPassword(MD5Util.getMD5String(operater.getPassword()));
				operater.setStatus(1);
				this.operaterService.save(operater);
			}
			return MessageOut.ADD_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.ADD_FAIL_MESSAGE;
	}

	/**
	 * 修改后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @return Out
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public Out<Object> update(Operater operater) {
		if (operater.getName() == null) {
			return MessageOut.UPDATE_FAIL_MESSAGE;
		}
		Operater curOperater = this.getCurrentOperater();
		try {
			Operater dbOperater = (Operater) this.operaterService.getByKey(operater.getId());
			if (dbOperater == null) {
				return MessageOut.RECORD_UN_EXIST_MESSAGE;
			} else {
				// 如果不是超级用户 或者 不是用户自己 ，无法修改
				if (dbOperater.getId().equals(curOperater.getId())) {
					if (this.operaterService.isExist(operater)) {
						return MessageOut.NAME_OR_CODE_EXIST_MESSAGE;
					} else {
						dbOperater.setName(operater.getName());
						if (operater.getPassword() != null && operater.getPassword().length() > 0) {
							dbOperater.setPassword(MD5Util.getMD5String(operater.getPassword()));
						}
						this.operaterService.update(dbOperater);
					}
				} else {
					return MessageOut.NO_PERMIT_UPDATE_MESSAGE;
				}
			}
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}
	/**
	 * 删除后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param ids
	 * @return Out
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public Out<Object> delete(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] idsArr = ids.split(",");
				for (String id : idsArr) {
					this.operaterService.deleteByKey(Long.parseLong(id));
				}
			}
			return MessageOut.DELETE_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.DELETE_FAIL_MESSAGE;
	}

	/**
	 * 查询
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @param pagination
	 * @return Out
	 */
	@RequestMapping("/search.do")
	@ResponseBody
	public Out<Operater> search(Operater operater, Pagination pagination) {
		try {
			return new DataOut<Operater>(this.operaterService.search(operater, pagination), pagination);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 获取我的信息
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @return Operater
	 */
	@RequestMapping("/getMyInfo.do")
	@ResponseBody
	public Operater getMyInfo() {
		Operater operater = new Operater();
		operater.setName(this.getCurrentOperater().getName());
		return operater;
	}

	/**
	 * 修改我的信息
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param operater
	 * @return Out
	 */
	@RequestMapping("/updateMyInfo.do")
	@ResponseBody
	public Out<Object> updateMyInfo(Operater operater) {
		try {
			Operater dbOperater = (Operater) this.operaterService.getByKey(this.getCurrentOperater().getId());
			if (dbOperater == null) {
				return MessageOut.UPDATE_FAIL_MESSAGE;
			}
			dbOperater.setName(operater.getName());
			if (operater.getPassword() != null && operater.getPassword().length() > 0) {
				dbOperater.setPassword(MD5Util.getMD5String(operater.getPassword()));
			}
			this.operaterService.update(dbOperater);
			((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true)
					.setAttribute(Context.USER_INFO, dbOperater);
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}

	/**
	 * 禁用后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param ids
	 * @return Out
	 */
	@RequestMapping("/disable.do")
	@ResponseBody
	public Out<Object> disable(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] tmp = ids.split(",");
				for (int i = 0; i < tmp.length; i++) {
					Operater dbOperater = (Operater) this.operaterService.getByKey(Long.parseLong(tmp[i]));
					dbOperater.setStatus(0);
					this.operaterService.update(dbOperater);
				}
			}
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}

	/**
	 * 启用后台用户
	 * 
	 * @author songkun
	 * @create 2014年6月28日 下午2:30:30
	 * @param ids
	 * @return Out
	 */
	@RequestMapping("/enable.do")
	@ResponseBody
	public Out<Object> enable(String ids) {
		try {
			if (ids != null && ids.length() > 0) {
				String[] tmp = ids.split(",");
				for (int i = 0; i < tmp.length; i++) {
					Operater dbOperater = (Operater) this.operaterService.getByKey(Long.parseLong(tmp[i]));
					dbOperater.setStatus(1);
					this.operaterService.update(dbOperater);
				}
			}
			return MessageOut.UPDATE_OK_MESSAGE;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageOut.UPDATE_FAIL_MESSAGE;
	}
}
