package com.bm.fmkj.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.JobLogMapper;
import com.bm.fmkj.domain.JobLog;
import com.bm.fmkj.service.IJobLogService;
import com.bm.fmkj.utils.Convert;
import com.github.pagehelper.PageHelper;

/**
 * 定时任务调度日志信息 服务层
 * 
 * @author youxun
 */
@Transactional
@Service
public class JobLogServiceImpl implements IJobLogService
{

    @Autowired
    private JobLogMapper jobLogMapper;
    
    public JobLogMapper getJobLogMapper() {
		return jobLogMapper;
	}

    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    public Pagenation<JobLog> selectJobLogList(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
		return PageUtil.toPagedResult(jobLogMapper.selectJobLogList(pageQuery.getParam()));
    }

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public JobLog selectJobLogById(Long jobLogId)
    {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    public void addJobLog(JobLog jobLog)
    {
        jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 批量删除调度日志信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJobLogByIds(String ids)
    {
        return jobLogMapper.deleteJobLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     */
    public int deleteJobLogById(Long jobId)
    {
        return jobLogMapper.deleteJobLogById(jobId);
    }

}
