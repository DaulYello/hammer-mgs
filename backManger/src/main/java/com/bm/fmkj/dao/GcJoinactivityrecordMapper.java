package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GcJoinactivityrecordMapper extends Mapper<GcJoinactivityrecord> {
    int updateJoinRecodeIsChain(List<Integer> list);
}