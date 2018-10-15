package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FmIntegralInfoMapper extends Mapper<FmIntegralInfo> {


    void allotRToUser(List<FmIntegralInfo> integralInfo);

}