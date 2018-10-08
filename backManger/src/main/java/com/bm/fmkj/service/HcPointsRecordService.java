package com.bm.fmkj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.HcPointsRecord;
import com.bm.fmkj.dao.HcPointsRecordMapper;

@Transactional
@Service
public class HcPointsRecordService {
	@Autowired
	private HcPointsRecordMapper hcpointsrecordMapper;

	public HcPointsRecordMapper getHcPointsRecordMapper() {
		return hcpointsrecordMapper;
	}

	
	public List<HcPointsRecord> queryOnlineNumber() {
		// TODO Auto-generated method stub
		return hcpointsrecordMapper.queryOnlineNumber();
	}

}