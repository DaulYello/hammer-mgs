package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmCntPool;
import com.bm.fmkj.dao.FmCntPoolMapper;

@Transactional
@Service
public class FmCntPoolService {
	@Autowired
	private FmCntPoolMapper fmcntpoolMapper;

	public FmCntPoolMapper getFmCntPoolMapper() {
		return fmcntpoolMapper;
	}

    public FmCntPool queryCntPool() {
		return  fmcntpoolMapper.queryCntPool();
    }
}