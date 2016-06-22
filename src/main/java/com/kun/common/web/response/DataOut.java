/**
 * Program  : DataOut.java
 * Author   : songkun
 * Create   : 2014年6月28日 下午1:59:34
 *
 */

package com.kun.common.web.response;

import java.util.List;

import com.kun.common.bean.Pagination;

/**
 * 数据输出类
 * 
 * @author songkun
 * @version 1.0.0
 * @2014年6月28日 下午1:59:34
 */
public class DataOut<T> implements Out<T> {

	private Pagination pagination;
	private List<T> rows;

	public DataOut(List<T> rows, Pagination pagination) {
		this.rows = rows;
		this.pagination = pagination;
	}

	public DataOut(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public List<T> getRows() {
		return rows;
	}

	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public boolean getSuccess() {
		return true;
	}

	@Override
	public int getTotal() {
		if (this.pagination != null) {
			return this.pagination.getTotalRows();
		}
		if (this.rows != null) {
			return this.rows.size();
		}
		return 0;
	}

	// extends
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
