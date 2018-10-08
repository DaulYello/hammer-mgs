package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcBuyticketrecordMapper;

@Transactional
@Service
public class GcBuyticketrecordService {
	@Autowired
	private GcBuyticketrecordMapper gcbuyticketrecordMapper;

	public GcBuyticketrecordMapper getGcBuyticketrecordMapper() {
		return gcbuyticketrecordMapper;
	}

}