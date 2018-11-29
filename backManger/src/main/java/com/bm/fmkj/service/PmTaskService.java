package com.bm.fmkj.service;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.domain.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class PmTaskService {

	private Logger LOGGER= LoggerFactory.getLogger(PmTaskService.class);

	@Autowired
	private PmTaskMapper pmtaskMapper;

	@Autowired
	private PmImageMapper pmImageMapper;

	@Autowired
	private PmPromptMapper pmPromptMapper;

	@Autowired
	private PmExtendMapper pmExtendMapper;

	@Autowired
	private PmStrategyMapper pmStrategyMapper;



	public PmTaskMapper getPmTaskMapper() {
		return pmtaskMapper;
	}

    public Pagenation<TaskDto> getTaskList(PageQuery pageQuery) {
		LOGGER.info("PmTaskService查询任务列表，参数："+ JSON.toJSONString(pageQuery));
		List<TaskDto> taskDtos = pmtaskMapper.getTaskList(pageQuery.getParam());
		return PageUtil.toPagedResult(taskDtos);
    }

	public int saveNewTask(PmTask task) {
		LOGGER.info("PmTaskService插入新的任务，参数："+ JSON.toJSONString(task));
		task.setCreateDate(new Date());
		/*task.setStatus((byte)0);*/
		return pmtaskMapper.insert(task);
	}

    public int updateTask(PmTask task) {
		LOGGER.info("PmTaskService修改任务，参数："+ JSON.toJSONString(task));
		task.setUpdateDate(new Date());
		return pmtaskMapper.updateByPrimaryKeySelective(task);
    }

	public HashMap<String, Object> issueTaskInfo(String id) {
		HashMap<String, Object> map = new HashMap<>();
		LOGGER.info("PmTaskService发布任务，参数："+ JSON.toJSONString(id));
		PmPrompt pmPrompt = new PmPrompt();
		pmPrompt.setTid(Integer.parseInt(id));
		LOGGER.info("1.通过任务id查询该任务是否有温馨提示");
		List<PmPrompt> prompts= pmPromptMapper.select(pmPrompt);
		if (prompts.size() <= 0){
			map.put("result",false);
			map.put("message","该任务没有添加温馨提示，请添加温馨提示再发布！");
			return map;
		}
		LOGGER.info("2.通过任务id查询该任务是否有添加了扩展字段");
		PmExtend extend = new PmExtend();
		extend.setTid(Integer.parseInt(id));
		List<PmExtend> extendList= pmExtendMapper.select(extend);
		if (extendList.size() <= 0){
			map.put("result",false);
			map.put("message","该任务没有添加扩展字段，请添加扩展字段再发布！");
			return map;
		}
		LOGGER.info("3.通过任务id查询该任务是否有添加了任务攻略");
		/*PmStrategy strategy = new PmStrategy();
		strategy.setTid(Integer.parseInt(id));*/
		List<PmStrategy> strategies = pmStrategyMapper.selectByTaskId(Integer.parseInt(id));
		if (strategies.size() <= 0){
			map.put("result",false);
			map.put("message","该任务没有任务攻略，请添加任务攻略再发布！");
			return map;
		}
		LOGGER.info("4.更新任务的status为发布状态");
		PmTask task = new PmTask();
		task.setId(Integer.parseInt(id));
		task.setStatus(1);
		task.setUpdateDate(new Date());
		boolean result = pmtaskMapper.updateByPrimaryKeySelective(task) > 0 ? true : false;
		if (!result){
			map.put("result",false);
			map.put("message","更新任务的status为发布状态时报错！");
			return map;
		}
		map.put("result",true);
		map.put("message","发布成功！");
		return map;
	}
}