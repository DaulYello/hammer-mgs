package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcCollectMapper;

@Transactional
@Service
public class GcCollectService {
	@Autowired
	private GcCollectMapper gccollectMapper;

	public GcCollectMapper getGcCollectMapper() {
		return gccollectMapper;
	}

}