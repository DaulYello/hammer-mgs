package com.bm.fmkj.service;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.PmImage;
import com.bm.fmkj.dao.PmImageMapper;
import com.bm.fmkj.domain.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmTask;
import com.bm.fmkj.dao.PmTaskMapper;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PmTaskService {

	private Logger LOGGER= LoggerFactory.getLogger(PmTaskService.class);

	@Autowired
	private PmTaskMapper pmtaskMapper;

	@Autowired
	private PmImageMapper pmImageMapper;



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
		return pmtaskMapper.insert(task);
	}
}