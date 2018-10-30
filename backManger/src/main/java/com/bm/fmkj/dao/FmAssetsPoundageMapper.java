package com.bm.fmkj.dao;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface FmAssetsPoundageMapper extends Mapper<FmAssetsPoundage> {

    double getCountPoundage();

    int updateYesterDayPDate(@Param("rate") int rate);
}