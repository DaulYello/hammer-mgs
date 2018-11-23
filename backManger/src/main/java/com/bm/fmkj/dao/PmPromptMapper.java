package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface PmPromptMapper extends Mapper<PmPrompt> {
    List<PmPrompt> getPromptInfo(HashMap<String, Object> param);
}