package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmOrderInfo;
import com.bm.fmkj.dao.FmOrderInfoMapper;

@Transactional
@Service
public class FmOrderInfoService {
	@Autowired
	private FmOrderInfoMapper fmorderinfoMapper;

	public FmOrderInfoMapper getFmOrderInfoMapper() {
		return fmorderinfoMapper;
	}

}