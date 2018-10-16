package com.bm.fmkj.service;

import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class FmCntInfoService {
	@Autowired
	private FmCntInfoMapper fmcntinfoMapper;

	@Autowired
	private FmRecyleLogMapper fmRecyleLogMapper;

	public FmCntInfoMapper getFmCntInfoMapper() {
		return fmcntinfoMapper;
	}

    public void allotCNToUser(List<FmCntInfo> onePhase) {
		fmcntinfoMapper.allotCNToUser(onePhase);
    }

	public void recyleToAccount(Double recyleNum, int poolId) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("poolId", poolId);
		param.put("recyleNum", recyleNum);
		param.put("recyleType", RecyleEnum.TYPE_CNT.status);
		param.put("takeType", TakeEnum.TYPE_ALLOT.status);
		param.put("dateTime", new Date());
		fmcntinfoMapper.updateFmCnt(param);
		fmcntinfoMapper.recyleToAccount(recyleNum);
		fmRecyleLogMapper.addCntLog(param);
	}
}