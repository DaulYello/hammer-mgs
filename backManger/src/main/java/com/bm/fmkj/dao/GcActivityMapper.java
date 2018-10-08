package com.bm.fmkj.dao;

import java.util.HashMap;
import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface GcActivityMapper extends Mapper<GcActivity> {
	
	/**
	 * 分页查询活动
	 */
	public List<GcActivity> queryAllActivityByPage(HashMap<String, Object> param);
}