package com.bm.fmkj.dao;

import com.bm.fmkj.domain.ProductDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface FmProductInfoMapper extends Mapper<FmProductInfo> {
    List<ProductDto> queryGoodsList(HashMap<String, Object> param);
}