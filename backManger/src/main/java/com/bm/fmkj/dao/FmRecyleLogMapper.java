package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;

public interface FmRecyleLogMapper extends Mapper<FmRecyleLog> {


    void addCntLog(HashMap<String, Object> param);
}