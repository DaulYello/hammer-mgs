package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.controller.PmPartController;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.domain.PmPartDto;
import com.bm.fmkj.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PmPartService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PmPartService.class);

	@Autowired
	private PmPartMapper pmpartMapper;

	@Autowired
	private PmTaskMapper pmTaskMapper;

	@Autowired
	private HcAccountMapper hcAccountMapper;

	@Autowired
	private FmRecyleLogMapper fmRecyleLogMapper;

	public PmPartMapper getPmPartMapper() {
		return pmpartMapper;
	}

    public Pagenation<PmPartDto> getPartPage(PageQuery pageQuery) {
		PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
		return PageUtil.toPagedResult(pmpartMapper.selectPartList(pageQuery.getParam()));
    }

	/**
	 * 审核通过需要向账户
	 * @param pmPart
	 * @return
	 */
	public int auditPart(PmPart pmPart) {
		Date now = new Date();
		pmPart.setUpdateDate(now);
		int row = pmpartMapper.updateByPrimaryKeySelective(pmPart);
		if(row > 0 && pmPart.getAuditStatus() ==  2){
			PmTask pmTask = pmTaskMapper.selectByPrimaryKey(pmPart.getTid());
			Double reward = pmTask.getReward();
			Double proportReward = 0D;
			int proport =  pmTask.getProportion();
			LOGGER.info("任务奖励用户占比：" + proport);
			if(StringUtils.isNotNull(proport) && proport > 0){
				reward = reward * proport / 100;
				proportReward = pmTask.getReward() - reward;
			}
			HcAccount hcAccount = hcAccountMapper.selectByPrimaryKey(pmPart.getUid());
			hcAccount.setCnt(hcAccount.getCnt() + reward);
			hcAccount.setUpdateDate(now);
			int update = hcAccountMapper.updateByPrimaryKeySelective(hcAccount);
			if(update > 0){
				LOGGER.info("审核通过向用户【"+hcAccount.getNickname()+"】发放"+reward+"CNT任务奖励成功");
				List<FmRecyleLog> fmRecyleLogList = new ArrayList<>();
				FmRecyleLog recyleLog = new FmRecyleLog();
				recyleLog.setUid(hcAccount.getId());
				recyleLog.setFriendId(hcAccount.getId());
				recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
				recyleLog.setTakeDate(now);
				recyleLog.setTakeNum(reward);
				recyleLog.setTakeMsg("任务审核通过后获得"+reward+"CNT奖励");
				recyleLog.setTakeType(TakeEnum.USER_REWARD.status);
				fmRecyleLogList.add(recyleLog);

				//获取公司账户
				if(proportReward > 0){
					HcAccount superAccount = hcAccountMapper.selectSuperAccount();
					superAccount.setCnt(superAccount.getCnt() + proportReward);
					superAccount.setUpdateDate(now);
					int superUpdate = hcAccountMapper.updateByPrimaryKeySelective(superAccount);
					if(superUpdate > 0){
						int p = 100 - proport;
						FmRecyleLog log = new FmRecyleLog();
						log.setUid(superAccount.getId());
						log.setFriendId(superAccount.getId());
						log.setRecyleType(RecyleEnum.TYPE_CNT.status);
						log.setTakeDate(now);
						log.setTakeNum(proportReward);
						log.setTakeMsg("【" + hcAccount.getNickname() + "】完成任务获得"+reward+"CNT任务奖励，公司账户获得" + proportReward + "CNT奖励, 占总奖励" +p+"%");
						log.setTakeType(TakeEnum.USER_REWARD.status);
						fmRecyleLogList.add(log);
					}
				}
				fmRecyleLogMapper.batchAddRecyleLog(fmRecyleLogList);

			}else{
				LOGGER.info("审核通过向用户"+hcAccount.getNickname()+"发放"+reward+"CNT任务奖励失败");
			}
		}
		return row;
	}
}