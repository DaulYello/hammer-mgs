package com.bm.fmkj.job.quartz;

import com.bm.fmkj.async.AsyncIntegralFactory;
import com.bm.fmkj.async.AsyncManager;
import com.bm.fmkj.dao.FmIntegralInfo;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.service.FmRpoolService;
import com.bm.fmkj.service.HcAccountService;
import com.bm.fmkj.utils.StringUtils;
import com.xl.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * R积分任务
 * 
 * @author youxun
 */
@Component("rIntegTask")
public class RIntegralTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(RIntegralTask.class);

	@Autowired
	private HcAccountService hcaccountService;

	@Autowired
	private FmRpoolService fmRpoolService;

	public void runRIntegral(String param) {
		if(StringUtils.isEmpty(param)){
			LOGGER.info("runRIntegral公司账户ID不能为空");
			return;
		}
		int uid = Integer.parseInt(param);
		int currentYear = DateUtil.getSysYear();
		FmRpool fmRpool  = fmRpoolService.queryRpoolByYear(currentYear);
		if (fmRpool == null || fmRpool.getRintegralNum() <= 0)
			return;
		//得到每天释放多少R积分
		Double dayRinteg = 0D;
		if(currentYear == 2018){
			dayRinteg = fmRpool.getRintegralNum() / 60;
		}else{
			dayRinteg = fmRpool.getRintegralNum() / 365;
		}
		Date nowTime = new Date();

		//每天分成6个阶段
		//int phase = getPhase(nowTime);

		//每个用户获取的R将均分6份，在24小时内分6个时段，在每个时段随机投放1份。
		Double everyRinteg = dayRinteg / 6;

		//0~1000 用户一共得到当天20%, 分配60%R积分、40%向下稀释给飞羽更高高阶段的用户
		Double oneR = everyRinteg * 0.2;
		Double oneAllotR = oneR * 0.6;
		Double oneDilutR = oneR * 0.4;

		//1000~3000 用户一共得到当天10%, 分配70%R积分、30%向下稀释给飞羽更高高阶段的用户
		Double twoR = everyRinteg * 0.1;
		Double twoAllotR = twoR * 0.7;
		Double twoDilutR = twoR * 0.3;

		//3000~6000 用户一共得到当天20%, 分配80%R积分、20%向下稀释给飞羽更高高阶段的用户
		Double threeR = everyRinteg * 0.2;
		Double threeAllotR = threeR * 0.8;
		Double threeDilutR = threeR * 0.2;

		//6000~10000 用户一共得到当天20%, 分配90%R积分、10%向下稀释给飞羽更高高阶段的用户
		Double fourR = everyRinteg * 0.3;
		Double fourAllotR = fourR * 0.9;
		Double fourDilutR = fourR * 0.1;


		//10000以上用户一共得到当天20%
		Double fiveR = everyRinteg * 0.2;

		//第二步：按用户飞羽区间统计用户

		//飞羽0~1000用户数
		List<FmIntegralInfo> onePhase = new ArrayList<>();
		//飞羽1000~3000用户数
		List<FmIntegralInfo> twoPhase = new ArrayList<>();
		//飞羽3000~6000用户数
		List<FmIntegralInfo> threePhase = new ArrayList<>();
		//飞羽6000~10000用户数
		List<FmIntegralInfo> fourPhase = new ArrayList<>();
		//飞羽10000以上用户数
		List<FmIntegralInfo> fivePhase = new ArrayList<>();

		List hcAccountList = hcaccountService.queryUserPointNum();
		if(StringUtils.isNotEmpty(hcAccountList)){
			for(int i = 0; i < hcAccountList.size(); i++){
				FmIntegralInfo fmIntegralInfo = new FmIntegralInfo();
				Map userMap = (Map)hcAccountList.get(i);
				Long id = (Long) userMap.get("id");
				Double pointNum = (Double) userMap.get("pointNum") == null ? 0 : (Double) userMap.get("pointNum");
				fmIntegralInfo.setCreateDate(nowTime);
				fmIntegralInfo.setUid(id.intValue());
				if(pointNum >= 0 && pointNum < 1000){
					onePhase.add(fmIntegralInfo);
				}
				if(pointNum >= 1000 && pointNum < 3000){
					twoPhase.add(fmIntegralInfo);
				}
				if(pointNum >= 3000 && pointNum < 6000){
					threePhase.add(fmIntegralInfo);
				}
				if(pointNum >= 6000 && pointNum < 10000){
					fourPhase.add(fmIntegralInfo);
				}
				if(pointNum >= 10000){
					fivePhase.add(fmIntegralInfo);
				}
			}
		}

		AsyncManager.me().execute(AsyncIntegralFactory.excuteOnePhase(onePhase, oneAllotR, fmRpool, uid));

		AsyncManager.me().execute(AsyncIntegralFactory.excuteTwoPhase(twoPhase, twoAllotR, oneDilutR * 0.25, fmRpool, uid));

		AsyncManager.me().execute(AsyncIntegralFactory.excuteThreePhase(threePhase, threeAllotR, oneDilutR * 0.25, twoDilutR * 0.3, fmRpool, uid));

		AsyncManager.me().execute(AsyncIntegralFactory.excuteFourPhase(fourPhase, fourAllotR, oneDilutR * 0.25, twoDilutR * 0.3, threeDilutR * 0.5, fmRpool, uid));

		AsyncManager.me().execute(AsyncIntegralFactory.excuteFivePhase(fivePhase, fiveR, oneDilutR * 0.25, twoDilutR * 0.4, threeDilutR * 0.5, fourDilutR, fmRpool, uid));

	}



}
