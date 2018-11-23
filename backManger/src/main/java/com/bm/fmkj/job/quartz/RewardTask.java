package com.bm.fmkj.job.quartz;

import com.bm.fmkj.async.AsyncManager;
import com.bm.fmkj.async.AsyncRecyleFactory;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.dao.HcAccount;
import com.bm.fmkj.service.FmIntegralInfoService;
import com.bm.fmkj.service.HcAccountService;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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

	public void runReward(String param) {
		if(StringUtils.isEmpty(param)){
			LOGGER.info("runRecyle公司账户ID不能为空");
			return;
		}
		int superUid = Integer.parseInt(param);//公司账户
        //周榜单分别奖励（邀请人数大于等于10）前1、2、3名20CNT、15CNT、10CNT
		List invitList = fmIntegralInfoService.queryLastInvitRank();
		if(StringUtils.isNotEmpty(invitList)){
			for(int i = 0; i < invitList.size(); i++){
				Map map = (Map)invitList.get(i);
				Long id = (Long) map.get("uid");
				int uid = id.intValue();
				String nickName = (String) map.get("nickname");
				Long invitingNum  = (Long) map.get("invitingNum");
				switch(i){
					case 0:
						//第一名
						if(invitingNum >= 10){

							List<FmRecyleLog> fmRecyleLogList = new ArrayList<>();

							FmRecyleLog recyleLog = new FmRecyleLog();
							recyleLog.setUid(uid);
							recyleLog.setFriendId(uid);
							recyleLog.setTakeNum(20D);
							recyleLog.setTakeDate(new Date());
							recyleLog.setTakeType(TakeEnum.RANKING_ALLOT.status);
							recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							recyleLog.setTakeMsg("上周邀请排行第一获得20CNT奖励");
							fmRecyleLogList.add(recyleLog);
							//公司账户

							FmRecyleLog fmRecyleLog = new FmRecyleLog();
							fmRecyleLog.setUid(superUid);
							fmRecyleLog.setFriendId(superUid);
							fmRecyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							fmRecyleLog.setTakeDate(new Date());
							fmRecyleLog.setTakeNum(20D);
							fmRecyleLog.setTakeMsg("【" + nickName + "】上周排行邀请排行第一获得20CNT奖励，同时公司账户增加20CNT");
							fmRecyleLog.setTakeType(TakeEnum.AUTO_NUM.status);
							fmRecyleLogList.add(fmRecyleLog);

							hcAccountService.updateCntById(uid, superUid, 20D, fmRecyleLogList);
						}
						break;
					case 1:
						//第2名
						if(invitingNum >= 10){

							List<FmRecyleLog> fmRecyleLogList = new ArrayList<>();

							FmRecyleLog recyleLog = new FmRecyleLog();
							recyleLog.setUid(uid);
							recyleLog.setFriendId(uid);
							recyleLog.setTakeNum(15D);
							recyleLog.setTakeDate(new Date());
							recyleLog.setTakeType(TakeEnum.RANKING_ALLOT.status);
							recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							recyleLog.setTakeMsg("上周邀请排行第二获得15CNT奖励");
							fmRecyleLogList.add(recyleLog);

							FmRecyleLog fmRecyleLog = new FmRecyleLog();
							fmRecyleLog.setUid(superUid);
							fmRecyleLog.setFriendId(superUid);
							fmRecyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							fmRecyleLog.setTakeDate(new Date());
							fmRecyleLog.setTakeNum(15D);
							fmRecyleLog.setTakeMsg("【" + nickName + "】上周排行邀请排行第二获得15CNT奖励，同时公司账户增加15CNT");
							fmRecyleLog.setTakeType(TakeEnum.AUTO_NUM.status);
							fmRecyleLogList.add(fmRecyleLog);

							hcAccountService.updateCntById(uid, superUid, 15D, fmRecyleLogList);
					    }
						break;
					case 2:
						//第3名
						if(invitingNum >= 10){

							List<FmRecyleLog> fmRecyleLogList = new ArrayList<>();

							FmRecyleLog recyleLog = new FmRecyleLog();
							recyleLog.setUid(uid);
							recyleLog.setFriendId(uid);
							recyleLog.setTakeNum(10D);
							recyleLog.setTakeDate(new Date());
							recyleLog.setTakeType(TakeEnum.RANKING_ALLOT.status);
							recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							recyleLog.setTakeMsg("上周邀请排行第三获得10CNT奖励");
							fmRecyleLogList.add(recyleLog);


							FmRecyleLog fmRecyleLog = new FmRecyleLog();
							fmRecyleLog.setUid(superUid);
							fmRecyleLog.setFriendId(superUid);
							fmRecyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
							fmRecyleLog.setTakeDate(new Date());
							fmRecyleLog.setTakeNum(10D);
							fmRecyleLog.setTakeMsg("【" + nickName + "】上周排行邀请排行第三获得10CNT奖励，同时公司账户增加10CNT");
							fmRecyleLog.setTakeType(TakeEnum.AUTO_NUM.status);
							fmRecyleLogList.add(fmRecyleLog);

							hcAccountService.updateCntById(uid, superUid, 10D, fmRecyleLogList);						}
						break;
					default:
						break;
				}
			}

		}



	}

}
