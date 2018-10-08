package com.bm.fmkj.dao;

import java.util.List;

import com.bm.fmkj.model.PageModel;

import tk.mybatis.mapper.common.Mapper;

public interface HcTransactioninfoMapper extends Mapper<HcTransactioninfo> {
	
	public List<HcTransactioninfo> selectAssetsAndTransaction(PageModel<HcTransactioninfo> ph);
}