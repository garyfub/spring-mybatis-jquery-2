package com.kun.app.operater.data;

import org.apache.ibatis.annotations.Param;

import com.kun.app.operater.model.Operater;
import com.kun.common.data.IMapper;
public interface OperaterMapper extends IMapper<Operater> {

	/**
	 * 根据名称或账号获取操作者
	 * 
	 * @author songkun
	 * @create 2014年4月25日 下午6:49:59
	 * @param operater
	 * @throws Exception
	 */
	public Operater getOneByName(@Param("object") Operater operater) throws Exception;
}