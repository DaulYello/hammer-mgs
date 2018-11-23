package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.controller.PmPartController;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.domain.PmPartDto;
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
				HcAccount superAccount = hcAccountMapper.selectSuperAccount();
				superAccount.setCnt(superAccount.getCnt() + reward);
				superAccount.setUpdateDate(now);
				int superUpdate = hcAccountMapper.updateByPrimaryKeySelective(superAccount);
				if(superUpdate > 0){
					FmRecyleLog fmRecyleLog = new FmRecyleLog();
					fmRecyleLog.setUid(superAccount.getId());
					fmRecyleLog.setFriendId(superAccount.getId());
					fmRecyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
					fmRecyleLog.setTakeDate(now);
					fmRecyleLog.setTakeNum(reward);
					fmRecyleLog.setTakeMsg("【" + hcAccount.getNickname() + "】完成任务获得"+reward+"CNT奖励，同时公司账户增加" + reward + "CNT");
					fmRecyleLog.setTakeType(TakeEnum.AUTO_NUM.status);
					fmRecyleLogList.add(fmRecyleLog);
				}
				fmRecyleLogMapper.batchAddRecyleLog(fmRecyleLogList);

			}else{
				LOGGER.info("审核通过向用户"+hcAccount.getNickname()+"发放"+reward+"CNT任务奖励失败");
			}
		}
		return row;
	}
}