package com.bm.fmkj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.SysOperateLog;
import com.bm.fmkj.dao.SysOperateLogMapper;
import com.bm.fmkj.domain.Job;
import com.bm.fmkj.utils.Convert;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class SysOperateLogService {
	@Autowired
	private SysOperateLogMapper sysoperatelogMapper;

	public SysOperateLogMapper getSysOperateLogMapper() {
		return sysoperatelogMapper;
	}
	
	public Pagenation<SysOperateLog> getOperaLog(PageQuery pageQuery){
		PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
		return PageUtil.toPagedResult(sysoperatelogMapper.getOperaLog(pageQuery.getParam()));
	}
	
	/**
	 * 批量删除日志
	 * @param ids
	 * @return
	 */
	public boolean deleteOperaLog(String ids) {
		
		Long[] logId = Convert.toLongArray(ids);
		return sysoperatelogMapper.deleteOperaLog(logId)>0?true:false;
	}

	public void insterLog(SysOperateLog operateLog){
		sysoperatelogMapper.insert(operateLog);
	}
}