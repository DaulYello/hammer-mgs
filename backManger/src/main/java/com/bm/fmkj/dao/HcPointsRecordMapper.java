package com.bm.fmkj.dao;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface HcPointsRecordMapper extends Mapper<HcPointsRecord> {

	public List<HcPointsRecord> queryOnlineNumber();
}