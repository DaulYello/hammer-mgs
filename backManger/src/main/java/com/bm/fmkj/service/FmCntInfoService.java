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

	@Autowired
	private FmCntPoolMapper fmCntPoolMapper;

	public FmCntInfoMapper getFmCntInfoMapper() {
		return fmcntinfoMapper;
	}

    public void allotCNToUser(List<FmCntInfo> onePhase) {
		fmcntinfoMapper.allotCNToUser(onePhase);
    }

	public void recyleToAccount(Double recyleNum, int poolId, int uid) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("poolId", poolId);
		param.put("recyleNum", recyleNum);
		param.put("recyleType", RecyleEnum.TYPE_CNT.status);
		param.put("takeType", TakeEnum.TYPE_ALLOT.status);
		param.put("dateTime", new Date());
		param.put("uid", uid);
		fmcntinfoMapper.updateFmCnt(param);
		fmcntinfoMapper.recyleToAccount(param);
		fmRecyleLogMapper.addRecyletLog(param);
	}

	public List<FmCntInfo> queryRecyleCNT() {
		return fmcntinfoMapper.queryRecyleCNT();
	}

	public void recyleCNT(int uid, Double totalNum, List<FmRecyleLog> recyleLogs) {
		FmCntPool fmCntPool = fmCntPoolMapper.queryYesterdayCNT();
		HashMap<String, Object> param = new HashMap<>();
		param.put("poolId", fmCntPool.getId());
		param.put("recyleNum", totalNum);
		param.put("dateTime", new Date());
		param.put("uid", uid);
		fmcntinfoMapper.updateFmCnt(param);
		fmcntinfoMapper.recyleToAccount(param);
		fmRecyleLogMapper.batchAddRecyleLog(recyleLogs);
	}
}