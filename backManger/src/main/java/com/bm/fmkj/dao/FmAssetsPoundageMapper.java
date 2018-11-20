package com.bm.fmkj.dao;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface FmAssetsPoundageMapper extends Mapper<FmAssetsPoundage> {

    double getCountPoundage();

    int updateYesterDayPDate(@Param("rate") double rate);
}