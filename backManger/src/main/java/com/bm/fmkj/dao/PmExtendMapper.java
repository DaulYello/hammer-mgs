package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface PmExtendMapper extends Mapper<PmExtend> {
    List<PmExtend> getExtendInfo(HashMap<String, Object> param);

    int saveExtendInfo(PmExtend extend);
}