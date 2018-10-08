package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

public interface BmListMapper extends Mapper<BmList> {
	
	public int setWhite(String[] idStr);
	
	
	public int setBlack(String[] idStr);
	
	
	public int withdrow(String uid);
}