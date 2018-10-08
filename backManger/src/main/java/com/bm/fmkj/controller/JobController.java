package com.bm.fmkj.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.domain.Job;
import com.bm.fmkj.domain.JobLog;
import com.bm.fmkj.service.IJobLogService;
import com.bm.fmkj.service.IJobService;
import com.bm.fmkj.utils.StringUtils;

/**
 * 调度任务信息操作处理
 * 
 */
@Controller
@RequestMapping("/fmkj/job")
public class JobController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
	
	 @Autowired
	 private IJobService jobService;
	 
	 @Autowired
	 private IJobLogService jobLogService;

	/**
	 * 任务列表
	 */
	@RequestMapping(value="queryJobPage",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<Job>> queryJobPage(@RequestParam HashMap<String, Object> params){
		LOGGER.info("queryJobPage-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<Job> pagedResult = jobService.selectJobList(pageQuery);
        return new BaseResult<Pagenation<Job>>(BaseResultEnum.SUCCESS,pagedResult);
	}
	
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="addJob",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> addJob(Job job){
		LOGGER.info("addJob-params={}", JSON.toJSONString(job));
		if(StringUtils.isNull(job)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = jobService.insertJobCron(job);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}
	
	/**
	 * 编辑修改
	 */
	@RequestMapping(value="updateJob",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> updateJob(Job job){
		LOGGER.info("updateJob-params={}", JSON.toJSONString(job));
		if(StringUtils.isNull(job)|| job.getJobId() == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = jobService.updateJobCron(job);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
	
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="deleteJob",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Boolean> deleteJob(@RequestParam String ids){
		LOGGER.info("deleteJob-params={}", JSON.toJSONString(ids));
		if(ids == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		try {
			jobService.deleteJobByIds(ids);
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		} catch (Exception e) {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(), "删除异常:" + e.getMessage(), false);
		}
	}
	
	/**
	 * 启用、改变状态
	 */
	@RequestMapping(value="changeStatus",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> changeStatus(Job job){
		LOGGER.info("updateJob-params={}", JSON.toJSONString(job));
		if(StringUtils.isNull(job) || job.getJobId() == null){
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int row = jobService.changeStatus(job);
		if(row > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR, false);
		}
	}
	
	/**
	 * 立即执行
	 */
	@RequestMapping(value="runJob",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> runJob(Job job){
		LOGGER.info("runJob-params={}", JSON.toJSONString(job));
		try {
			if(StringUtils.isNull(job) || job.getJobId() == null){
				return new BaseResult<Boolean>(BaseResultEnum.BLANK, false);
			}
			int row = jobService.run(job);
			if(row > 0) {
				return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
			}else {
				return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
			}
		} catch (Exception e) {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(), "执行异常:" + e.getMessage(), false);			
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="deleteJobLog",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Boolean> deleteJobLog(@RequestParam String ids){
		LOGGER.info("deleteJobLog-params={}", JSON.toJSONString(ids));
		if(StringUtils.isEmpty(ids)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK.getStatus(), "参数为空", false);
		}
		try {
			int result = jobLogService.deleteJobLogByIds(ids);
			if(result > 0) {
				return new BaseResult<Boolean>(BaseResultEnum.SUCCESS.getStatus(), "删除成功", true);
			}
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(),"删除失败", false);
		} catch (Exception e) {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(), "删除异常:" + e.getMessage(), false);
		}
	}
	
	/**
	 * 任务列表
	 */
	@RequestMapping(value="queryJobLogPage",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<JobLog>> queryJobLogPage(@RequestParam HashMap<String, Object> params){
		LOGGER.info("queryJobLogPage-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<JobLog> pagedResult = jobLogService.selectJobLogList(pageQuery);
        return new BaseResult<Pagenation<JobLog>>(BaseResultEnum.SUCCESS,pagedResult);
	}
	
}
