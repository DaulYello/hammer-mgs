package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcActivitytype;
import com.bm.fmkj.dao.GcActivitytypeMapper;

@Transactional
@Service
public class GcActivitytypeService {
	@Autowired
	private GcActivitytypeMapper gcactivitytypeMapper;

	public GcActivitytypeMapper getGcActivitytypeMapper() {
		return gcactivitytypeMapper;
	}
	
	public GcActivitytype getGcActivitytypeId(int id) {
		return gcactivitytypeMapper.selectByPrimaryKey(id);
	}
}