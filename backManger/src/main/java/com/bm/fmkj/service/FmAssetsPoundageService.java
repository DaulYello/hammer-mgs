package com.bm.fmkj.service;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.utils.StringUtils;
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

	public HashMap<String,Object> getCountPoundage(String param){

		LOGGER.info("定时任务参数："+ JSON.toJSONString(param));

		String [] params = param.split("-");
		String cp_account = params[0];
		String rateStr = params[1];

		HashMap<String,Object> map = new HashMap<>();
		double count = fmassetspoundageMapper.getCountPoundage();;
		LOGGER.info("昨天平台活动产生的手续费："+ count);
		if(count==0.0){
			map.put("status",false);
			map.put("message","昨天的没有活动产生的CNT。");
			return map;
		}
		double rateDouble = Double.valueOf(rateStr)/100;
		double releaseCnter = count * rateDouble;
		BigDecimal bigDecimal = new BigDecimal(count * rateDouble);
		double releaseCnt = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		LOGGER.info("释放的cnt："+ releaseCnt);
		FmCntPool cntPool = new FmCntPool();
		cntPool.setCntPool(releaseCnt);
		cntPool.setCreateDate(new Date());
		Calendar data = Calendar.getInstance();
		cntPool.setYear(data.get(Calendar.YEAR));
		int saveCnt = cntPoolMapper.insert(cntPool);
		if(saveCnt<=0){
			LOGGER.info("cntPool表中插入CNT时报错！");
			throw new RuntimeException();
		}
		LOGGER.info("fm_cnt_pool保存要释放的cnt："+ saveCnt);

		int rate= Integer.parseInt(rateStr);
		boolean result = fmassetspoundageMapper.updateYesterDayPDate(rate) >0 ? true : false;
		if(!result){
			LOGGER.info("批量更新fm_assets_poundage：的释放比率和更新时间失败！");
			throw new RuntimeException();
		}

		double subside = (double)Math.round((count-releaseCnt)*100)/100;
		HcAccount hcAccount= accountMapper.selectByPrimaryKey(Integer.parseInt(cp_account));
		if(hcAccount == null){
			LOGGER.info("公司账户的ID="+Integer.parseInt(cp_account)+",查出来的账户为空！");
			throw new RuntimeException();
		}
		LOGGER.info("沉淀的cnt："+ releaseCnt);
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
			recyleLog.setTakeMsg("确认收货【"+account.getNickname()+"】获得" + subside + "CNT");
			recyleLogMapper.insert(recyleLog);

			map.put("status",cntAccount);
			map.put("message","昨天的产生的CNT已经分布完。");
			return map;
		}else{
			LOGGER.info("更新公司CNT时报错！");
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {
		/*Scanner scanner = new Scanner(System.in);*/
		Double ss = 30.684;
		BigDecimal bigDecimal = new BigDecimal(ss);
		double f1 = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		double f2 = f1/100;
		BigDecimal b = new BigDecimal(f2);
		double f3 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f3);

	}

}