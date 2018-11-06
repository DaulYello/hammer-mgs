package com.bm.fmkj.service;

import com.bm.fmkj.dao.FmIntegralInfo;
import com.bm.fmkj.dao.FmIntegralInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class FmIntegralInfoService {
	@Autowired
	private FmIntegralInfoMapper fmintegralinfoMapper;

	public FmIntegralInfoMapper getFmIntegralInfoMapper() {
		return fmintegralinfoMapper;
	}

	public void allotRToUser(List<FmIntegralInfo> integralInfo) {

		fmintegralinfoMapper.allotRToUser(integralInfo);
	}

    public List<FmIntegralInfo> queryRBefore() {
		return  fmintegralinfoMapper.queryRBefore();
    }

    public List queryLastInvitRank() {

		return fmintegralinfoMapper.queryLastInvitRank();
    }
}