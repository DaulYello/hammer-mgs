package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FmReleaseRuleMapper extends Mapper<FmReleaseRule> {

    /**
     * 查询所有的积分规则
     * @return
     */
    List<FmReleaseRule> selectIntegralRules();

}
