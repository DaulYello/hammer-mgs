package com.bm.fmkj.dao;

import java.util.HashMap;
import java.util.List;

import tk.mybatis.mapper.common.Mapper;
/**
 * 操作日志
 * @author huangshuang
 */
public interface SysOperateLogMapper extends Mapper<SysOperateLog> {

	/**
	 * 
	 * @param hashMap
	 * @return
	 */
	public List<SysOperateLog> getOperaLog(HashMap<String, Object> hashMap);
	
	public int deleteOperaLog(Long[] logId);
}