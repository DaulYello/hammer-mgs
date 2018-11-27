package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;

public interface PmImageMapper extends Mapper<PmImage> {

    int insertImage(PmImage image);

    int updateStrategyImage(HashMap<String, Object> paramsMap);
}