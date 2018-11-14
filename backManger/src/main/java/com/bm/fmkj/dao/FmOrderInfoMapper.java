package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FmOrderInfoMapper extends Mapper<FmOrderInfo> {

    /**
     * 根据商品id查询订单明细
     * @param pid
     * @return
     */
    List<FmOrderInfo> queryOdersByPId(Integer pid);
}