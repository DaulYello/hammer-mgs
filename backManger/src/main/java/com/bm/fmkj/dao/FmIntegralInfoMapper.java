package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface FmIntegralInfoMapper extends Mapper<FmIntegralInfo> {


    void allotRToUser(List<FmIntegralInfo> integralInfo);

    List<FmIntegralInfo> queryRBefore();

    List queryLastInvitRank();

    void updateFmIntegral();

    void recyleRToAccount(HashMap<String, Object> param);
}