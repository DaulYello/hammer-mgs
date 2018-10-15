package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FmCntInfoMapper extends Mapper<FmCntInfo> {

    void allotCNToUser(List<FmCntInfo> fmCntInfo);
}