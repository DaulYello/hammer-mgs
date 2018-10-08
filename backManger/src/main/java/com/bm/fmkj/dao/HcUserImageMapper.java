package com.bm.fmkj.dao;

import java.util.HashMap;
import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface HcUserImageMapper extends Mapper<HcUserImage> {
	
	/**
	 * 更新HcUserImage的审核状态
	 * @param hi
	 * @return
	 */
	public boolean updateById(HcUserImage hi); 
	
	public List<HcUserImage> getUserRealInfo(HashMap<String, Object> params);
}