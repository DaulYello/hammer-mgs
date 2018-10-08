package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcJoinactivityrecordMapper;

@Transactional
@Service
public class GcJoinactivityrecordService {
	@Autowired
	private GcJoinactivityrecordMapper gcjoinactivityrecordMapper;

	public GcJoinactivityrecordMapper getGcJoinactivityrecordMapper() {
		return gcjoinactivityrecordMapper;
	}

}