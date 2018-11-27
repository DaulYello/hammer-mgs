package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface GcActivityMapper extends Mapper<GcActivity> {

    /**
     * 分页查询活动
     */
    List<GcActivity> queryAllActivityByPage(HashMap<String, Object> param);

    List<GcActivity> queryActivityByStatus();

    int updateActivte(List<Integer> list);
}

