package com.bm.fmkj.model;

import java.io.Serializable;

@SuppressWarnings("hiding")
public class PageModel<T> implements Serializable  {
	private static final long serialVersionUID = 1L;

	private Integer start;//开始
	
	private Integer page;//页数
	
	private Integer row=20;//每页显示数
	
	private Integer end;//结束
	
	//名单状态  0无   1白名单  2黑名单
	//活动状态--0.未审核 1.通过上线   2.通过已下线  3.未通过
	private Integer status;
	
	//实体查询
	private T entity;
	

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStart() {
		start = (page-1)*row;
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getEnd() {
		end = page * row;
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
}
