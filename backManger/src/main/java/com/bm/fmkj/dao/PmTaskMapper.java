package com.bm.fmkj.dao;

import com.bm.fmkj.domain.TaskDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface PmTaskMapper extends Mapper<PmTask> {

    List<TaskDto> getTaskList(HashMap<String, Object> param);

    List<PmTask> queryTaskList();
}