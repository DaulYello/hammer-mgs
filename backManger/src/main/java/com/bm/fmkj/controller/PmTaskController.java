package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.PmTask;
import com.bm.fmkj.domain.TaskDto;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.xl.utils.BaseController;
import com.bm.fmkj.service.PmTaskService;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/PmTask")
public class PmTaskController extends BaseController {

	private Logger LOGGER= LoggerFactory.getLogger(PmTaskController.class);

	@Autowired
	private PmTaskService pmtaskService;

	/**
	 * 查询任务列表
	 * @param params
	 * @return
	 */
	//@BackLog(module= LogConstant.BACK_USER, actionDesc = "用户登录")
	@RequestMapping(value="getTaskList",method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<TaskDto>> getTaskList(@RequestParam HashMap<String,Object> params) {

		LOGGER.info("queryGoodsList-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));

		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<TaskDto> pageResult = pmtaskService.getTaskList(pageQuery);
		return new BaseResult<Pagenation<TaskDto>>(BaseResultEnum.SUCCESS,pageResult);
	}

	/**
	 * 新增任务
	 */
	@RequestMapping(value="addTask",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> addTask(PmTask task){
 		LOGGER.info("addTask-params={}", JSON.toJSONString(task));
		if(StringUtils.isNull(task)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = pmtaskService.saveNewTask(task);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}
	/**
	 * 修改任务
	 */
	@RequestMapping(value="updateTask",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> updateTask(PmTask task){
 		LOGGER.info("addTask-params={}", JSON.toJSONString(task));
		if(StringUtils.isNull(task)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = pmtaskService.updateTask(task);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}


	@RequestMapping(value="issueTaskInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<HashMap<String,Object>> issueTaskInfo(@RequestParam String id) {
		LOGGER.info("issueTaskInfo-params={}", JSON.toJSONString(id));
		HashMap<String, Object> map = new HashMap<>();
		if(StringUtils.isNull(id)) {
			map.put("result",false);
			map.put("message","参数不能为空!");
			return new BaseResult<HashMap<String,Object>>(BaseResultEnum.BLANK,map);
		}
		HashMap<String,Object> mapResult = pmtaskService.issueTaskInfo(id);
		if (mapResult.get("result").equals(false)){
			return new BaseResult<HashMap<String,Object>>(BaseResultEnum.ERROR,mapResult);
		}
		return new BaseResult<HashMap<String,Object>>(BaseResultEnum.SUCCESS,mapResult);
	}
}