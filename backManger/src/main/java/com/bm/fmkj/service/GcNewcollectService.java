package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcNewcollectMapper;

@Transactional
@Service
public class GcNewcollectService {
	@Autowired
	private GcNewcollectMapper gcnewcollectMapper;

	public GcNewcollectMapper getGcNewcollectMapper() {
		return gcnewcollectMapper;
	}

}