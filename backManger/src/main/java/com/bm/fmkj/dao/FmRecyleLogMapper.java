package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface FmRecyleLogMapper extends Mapper<FmRecyleLog> {


    void addRecyletLog(HashMap<String, Object> param);

    void batchAddRecyleLog(List<FmRecyleLog> recyleLogs);
}