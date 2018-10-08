package com.bm.fmkj.base;

import java.util.Collections;
import java.util.List;
 /**
  * 
  * @author youxun
  *
  * @param <T>
  * 
  * 分页工具类
  */
public class Pagenation<T>{
	
    private List<T> dataList = Collections.emptyList();//数据
	
	private int pageNo;//当前页
	
	private int pageSize;//条数
	
	private long total;//总条数
	
	private long pages;//总页面数目
	
    
	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

}
