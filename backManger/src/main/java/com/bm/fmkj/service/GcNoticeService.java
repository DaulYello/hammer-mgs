package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcNoticeMapper;

@Transactional
@Service
public class GcNoticeService {
	@Autowired
	private GcNoticeMapper gcnoticeMapper;

	public GcNoticeMapper getGcNoticeMapper() {
		return gcnoticeMapper;
	}

}