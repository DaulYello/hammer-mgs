package com.bm.fmkj.service;

import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.controller.GcActivityController;
import com.bm.fmkj.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.EntityWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class GcJoinactivityrecordService {
	@Autowired
	private GcJoinactivityrecordMapper gcjoinactivityrecordMapper;

	@Autowired
	private GcActivityMapper activityMapper;
	@Autowired
	private HcAccountMapper accountMapper;
	@Autowired
	private FmRecyleLogMapper fmRecyleLogMapper;

	private Logger LOGGER= LoggerFactory.getLogger(GcJoinactivityrecordService.class);


	public GcJoinactivityrecordMapper getGcJoinactivityrecordMapper() {
		return gcjoinactivityrecordMapper;
	}

	public HashMap<String,Object> grantCredits(int aid){

		HashMap<String,Object> map = new HashMap<>();
		LOGGER.debug("查询活动，获取活动的参与人数");
		GcActivity activity= activityMapper.selectByPrimaryKey(aid);
		if(activity == null){
			map.put("status",false);
			map.put("message","为获取活动的参与人数，查询活动失败！");
			return map;
		}
		LOGGER.debug("查询活动参与记录，获取活动的参与人");
		GcJoinactivityrecord gcJoinactivityrecord = new GcJoinactivityrecord();
		gcJoinactivityrecord.setAid(aid);
		List<GcJoinactivityrecord> joinactivityrecords= gcjoinactivityrecordMapper.select(gcJoinactivityrecord);
		if(joinactivityrecords.size()==0){
			map.put("status",false);
			map.put("message","查询活动参与记录为0,活动aid="+aid);
			return map;
		}
		if(activity.getNum() != joinactivityrecords.size()){
			map.put("status",false);
			map.put("message","查询活动参与记录为0,活动aid="+aid);
			return map;
		}
		LOGGER.debug("给参与互动的用户发放R积分");
		List<FmRecyleLog> recyleLogs = new ArrayList<>();
		for(GcJoinactivityrecord joinactivityrecord : joinactivityrecords){
			if(joinactivityrecord.getUid() != activity.getGetid()){
				HcAccount hcAccount = accountMapper.selectByPrimaryKey(joinactivityrecord.getUid());
				LOGGER.debug("发积分之前用户的R积分R="+hcAccount.getMyP());
				hcAccount.setId(joinactivityrecord.getUid());
				hcAccount.setMyP(hcAccount.getMyP()+activity.getPar());
				LOGGER.debug("应发积分R="+activity.getPar());
				LOGGER.debug("发完积分后用户的总积分R="+hcAccount.getPassword());
				boolean result = result = accountMapper.updateByPrimaryKeySelective(hcAccount) > 0 ? true : false;
				if(result){
					map.put("status",false);
					map.put("message","更新用户的积分时报错,用户的id="+joinactivityrecord.getUid());
					return map;
				}
				LOGGER.debug("记录用户反回的R积分，用户id="+hcAccount.getId());
				FmRecyleLog recyleLog = new FmRecyleLog();
				recyleLog.setUid(hcAccount.getId());
				recyleLog.setFriendId(hcAccount.getId());
				recyleLog.setRecyleType(2);
				recyleLog.setTakeDate(new Date());
				recyleLog.setTakeNum(activity.getPar());
				recyleLog.setTakeType(TakeEnum.USER_GET.status);
				recyleLogs.add(recyleLog);
			}
		}
		fmRecyleLogMapper.batchAddRecyleLog(recyleLogs);
		map.put("status",true);
		map.put("message","竟锤成功！");
		return map;
	}
}