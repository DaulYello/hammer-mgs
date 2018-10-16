package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

public interface FmCntPoolMapper extends Mapper<FmCntPool> {
    FmCntPool queryCntPool();

    FmCntPool queryYesterdayCNT();
}