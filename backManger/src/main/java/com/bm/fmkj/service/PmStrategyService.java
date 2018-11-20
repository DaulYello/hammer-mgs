package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmStrategy;
import com.bm.fmkj.dao.PmStrategyMapper;

@Transactional
@Service
public class PmStrategyService {
	@Autowired
	private PmStrategyMapper pmstrategyMapper;

	public PmStrategyMapper getPmStrategyMapper() {
		return pmstrategyMapper;
	}

}