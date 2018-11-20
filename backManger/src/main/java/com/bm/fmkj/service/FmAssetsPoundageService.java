package com.bm.fmkj.service;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.utils.StringUtils;
import com.xl.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

@Transactional
@Service
public class FmAssetsPoundageService {
	@Autowired
	private FmAssetsPoundageMapper fmassetspoundageMapper;
	@Autowired
	private FmCntPoolMapper cntPoolMapper;
	@Autowired
	private HcAccountMapper accountMapper;
	@Autowired
	private FmRecyleLogMapper recyleLogMapper;


	private static final Logger LOGGER = LoggerFactory.getLogger(FmAssetsPoundageService.class);

	public FmAssetsPoundageMapper getFmAssetsPoundageMapper() {
		return fmassetspoundageMapper;
	}

	public void allotYesterdayActivityProCnt(String param){

		LOGGER.info("定时任务参数："+ JSON.toJSONString(param));

		String [] params = param.split("-");
		String cp_account = params[0];
		String rateStr = params[1];

		HashMap<String,Object> map = new HashMap<>();
		double totalCnt = fmassetspoundageMapper.getCountPoundage();;
		LOGGER.info("昨天平台活动产生的手续费："+ totalCnt);
		double rateDouble = Double.valueOf(rateStr)/100;
		double releaseCnter = totalCnt * rateDouble;
		LOGGER.info("释放的cnt："+ releaseCnter);
		FmCntPool cntPool = new FmCntPool();
		cntPool.setCntPool(releaseCnter);
		cntPool.setCreateDate(new Date());
		cntPool.setYear(DateUtil.getSysYear());
		int saveCnt = cntPoolMapper.insert(cntPool);
		if(saveCnt<=0){
			throw new RuntimeException("cntPool表中插入CNT时报错!");
		}
		if(totalCnt==0){
			return;
		}
		LOGGER.info("fm_cnt_pool保存要释放的cnt返回影响的行数："+ saveCnt);
		boolean result = fmassetspoundageMapper.updateYesterDayPDate(rateDouble) >0 ? true : false;
		if(!result){
			throw new RuntimeException("批量更新fm_assets_poundage：的释放比率和更新时间失败！");
		}

		double subside = totalCnt-releaseCnter;
		HcAccount hcAccount= accountMapper.selectByPrimaryKey(Integer.parseInt(cp_account));
		if(hcAccount == null){
			throw new RuntimeException("公司账户的ID="+Integer.parseInt(cp_account)+",查出来的账户为空！");
		}
		LOGGER.info("沉淀的cnt："+ releaseCnter);
		HcAccount account = new HcAccount();
		account.setId(Integer.parseInt(cp_account));
		account.setCnt(hcAccount.getCnt()+subside);
		account.setUpdateDate(new Date());
		LOGGER.info("保存沉淀的cnt到公司账户");
		boolean cntAccount = accountMapper.updateByPrimaryKeySelective(account) > 0 ? true : false;
		if(cntAccount){
			FmRecyleLog recyleLog = new FmRecyleLog();
			recyleLog.setUid(Integer.parseInt(cp_account));
			recyleLog.setTakeDate(new Date());
			recyleLog.setTakeNum(subside);
			recyleLog.setFriendId(Integer.parseInt(cp_account));
			recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
			recyleLog.setTakeType(TakeEnum.TAKE_GOODS.status);
			recyleLog.setTakeMsg("确认收货【"+hcAccount.getNickname()+"】获得" + subside + "CNT");
			recyleLogMapper.insert(recyleLog);
		}else{
			LOGGER.info("更新公司CNT时报错！");
			throw new RuntimeException("更新公司CNT时报错！");
		}
	}
}