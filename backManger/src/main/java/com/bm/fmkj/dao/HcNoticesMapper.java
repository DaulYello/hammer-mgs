package com.bm.fmkj.dao;

import java.util.HashMap;
import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface HcNoticesMapper extends Mapper<HcNotices> {
	
	public List<HcNotices> selectNotices(HashMap<String, Object> hashMap);
}