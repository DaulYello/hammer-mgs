package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface FmCntInfoMapper extends Mapper<FmCntInfo> {

    void allotCNToUser(List<FmCntInfo> fmCntInfo);

    void recyleToAccount(HashMap<String, Object> param);

    void updateFmCnt(HashMap<String, Object> param);

    List<FmCntInfo> queryRecyleCNT();
}