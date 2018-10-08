package com.bm.fmkj.dao;

import java.util.HashMap;
import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface GcTicketMapper extends Mapper<GcTicket> {
	/*
	 * 查询门票的信息
	 */
	
	public List<GcTicket> selectTicketInfo(HashMap<String, Object> param);
}