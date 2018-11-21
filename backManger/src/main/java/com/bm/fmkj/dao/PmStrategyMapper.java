package com.bm.fmkj.dao;

import com.bm.fmkj.domain.PmStrategyDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface PmStrategyMapper extends Mapper<PmStrategy> {

    List<PmStrategyDto> selectStrategyList(HashMap<String, Object> param);

    int deleteStrategy(String[] ids);
}