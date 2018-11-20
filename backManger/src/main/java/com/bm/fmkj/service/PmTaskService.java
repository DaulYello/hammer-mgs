package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmTask;
import com.bm.fmkj.dao.PmTaskMapper;

@Transactional
@Service
public class PmTaskService {
	@Autowired
	private PmTaskMapper pmtaskMapper;

	public PmTaskMapper getPmTaskMapper() {
		return pmtaskMapper;
	}

}