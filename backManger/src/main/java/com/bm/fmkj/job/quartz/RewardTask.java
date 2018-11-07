package com.bm.fmkj.job.quartz;

import com.bm.fmkj.async.AsyncManager;
import com.bm.fmkj.async.AsyncRecyleFactory;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.service.FmIntegralInfoService;
import com.bm.fmkj.service.HcAccountService;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 周榜单分别奖励（邀请人数大于等于10）前1、2、3名20CNT、15CNT、10CNT
 * 
 * @author youxun
 */
@Component("rewardTask")
public class RewardTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardTask.class);

	@Autowired
	private FmIntegralInfoService fmIntegralInfoService;

	@Autowired
	private HcAccountService hcAccountService;

	public void runReward() {

        //周榜单分别奖励（邀请人数大于等于10）前1、2、3名20CNT、15CNT、10CNT
		List invitList = fmIntegralInfoService.queryLastInvitRank();
		if(StringUtils.isNotEmpty(invitList)){
			for(int i = 0; i < invitList.size(); i++){
				Map map = (Map)invitList.get(i);
				Integer uid = (Integer) map.get("uid");
				Long invitingNum  = (Long) map.get("invitingNum");
				switch(i){
					case 0:
						//第一名
						if(invitingNum >= 10){
							FmRecyleLog recyleLog = new FmRecyleLog();
							recyleLog.setUid(uid);
							recyleLog.setFriendId(uid);
							recyleLog.setTakeNum(20D);
							recyleLog.setTakeDate(new Date());
							recyleLog.setTakeType(TakeEnum.RANKING_ALLOT.status);
							recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							recyleLog.setTakeMsg("上周邀请排行第一获得20CNT奖励");
							hcAccountService.updateCntById(uid, recyleLog);
						}
						break;
					case 1:
						//第2名
						if(invitingNum >= 10){
							FmRecyleLog recyleLog = new FmRecyleLog();
							recyleLog.setUid(uid);
							recyleLog.setFriendId(uid);
							recyleLog.setTakeNum(15D);
							recyleLog.setTakeDate(new Date());
							recyleLog.setTakeType(TakeEnum.RANKING_ALLOT.status);
							recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							recyleLog.setTakeMsg("上周邀请排行第一获得15CNT奖励");
							hcAccountService.updateCntById(uid, recyleLog);
					    }
						break;
					case 2:
						//第3名
						if(invitingNum >= 10){
							FmRecyleLog recyleLog = new FmRecyleLog();
							recyleLog.setUid(uid);
							recyleLog.setFriendId(uid);
							recyleLog.setTakeNum(10D);
							recyleLog.setTakeDate(new Date());
							recyleLog.setTakeType(TakeEnum.RANKING_ALLOT.status);
							recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							recyleLog.setTakeMsg("上周邀请排行第一获得10CNT奖励");
							hcAccountService.updateCntById(uid,recyleLog);
						}
						break;
					default:
						break;
				}
			}

		}



	}

}
