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

import com.xl.utils.BaseController;
import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.SysOperateLog;
import com.bm.fmkj.service.SysOperateLogService;

@Controller
@RequestMapping("/fmkj/SysOperateLog")
public class SysOperateLogController extends BaseController {
	@Autowired
	private SysOperateLogService sysoperatelogService;

	private static final Logger LOGGER = LoggerFactory.getLogger(SysOperateLogController.class);
	/**
	 * 业务操作日志列表 
	 */
	@RequestMapping(value="getQueryOperaLog",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<SysOperateLog>> getQueryOperaLog(@RequestParam HashMap<String, Object> params){
		LOGGER.info("getQueryOperaLog", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<SysOperateLog> pagedResult = sysoperatelogService.getOperaLog(pageQuery);
        return new BaseResult<Pagenation<SysOperateLog>>(BaseResultEnum.SUCCESS,pagedResult);
	}
	
	/**
	 * 删除业务操作日志
	 */
	@RequestMapping(value="deleteOperaLog",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Boolean> deleteOperaLog(@RequestParam String ids){
		LOGGER.info("deleteOperaLog={}", JSON.toJSONString(ids));
		if(ids == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		try {
			sysoperatelogService.deleteOperaLog(ids);
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		} catch (Exception e) {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(), "删除异常:" + e.getMessage(), false);
		}
	}
}