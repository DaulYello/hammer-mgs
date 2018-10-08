package com.bm.fmkj.base;

import java.io.Serializable;
import java.util.HashMap;

public class PageQuery implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private int pageNo;
	
	private int pageSize = 20;
	
	private HashMap<String, Object> param;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public HashMap<String, Object> getParam() {
		return param;
	}

	public void setParam(HashMap<String, Object> param) {
		this.param = param;
	}

}
