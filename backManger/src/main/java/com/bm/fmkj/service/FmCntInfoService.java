package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmCntInfo;
import com.bm.fmkj.dao.FmCntInfoMapper;

import java.util.List;

@Transactional
@Service
public class FmCntInfoService {
	@Autowired
	private FmCntInfoMapper fmcntinfoMapper;

	public FmCntInfoMapper getFmCntInfoMapper() {
		return fmcntinfoMapper;
	}

    public void allotCNToUser(List<FmCntInfo> onePhase) {
		fmcntinfoMapper.allotCNToUser(onePhase);
    }
}