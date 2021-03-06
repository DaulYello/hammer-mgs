package com.bm.fmkj.job.quartz;

import com.bm.fmkj.async.AsyncCntFactory;
import com.bm.fmkj.async.AsyncManager;
import com.bm.fmkj.dao.FmCntInfo;
import com.bm.fmkj.dao.FmCntPool;
import com.bm.fmkj.service.FmCntPoolService;
import com.bm.fmkj.service.HcAccountService;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * cnt任务
 * 
 * @author youxun
 */
@Component("cntTask")
public class CntTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(CntTask.class);

	@Autowired
	private HcAccountService hcaccountService;

	@Autowired
	private FmCntPoolService fmCntPoolService;

	//参数为公司账户ID
	public void runExcute(String param) {
		if(StringUtils.isEmpty(param)){
			LOGGER.info("runExcute公司账户ID不能为空");
			return;
		}
		int uid = Integer.parseInt(param);
		FmCntPool fmCntPool = fmCntPoolService.queryCntPool();
		if(fmCntPool == null || fmCntPool.getCntPool() <= 0){
			LOGGER.info("资金池没有可释放的CNT");
            return;
		}

		Date nowTime = new Date();

		Double cntPool = fmCntPool.getCntPool() / 2;
		int poolId = fmCntPool.getId();



		//第二步：按用户飞羽区间统计用户
		//飞羽0~1000用户数
		List<FmCntInfo> onePhase = new ArrayList<>();
		//飞羽1000~3000用户数
		List<FmCntInfo> twoPhase = new ArrayList<>();
		//飞羽3000~6000用户数
		List<FmCntInfo> threePhase = new ArrayList<>();
		//飞羽6000~10000用户数
		List<FmCntInfo> fourPhase = new ArrayList<>();
		//飞羽10000以上用户数
		List<FmCntInfo> fivePhase = new ArrayList<>();

		List hcAccountList = hcaccountService.queryUserPointNum();
		if(StringUtils.isNotEmpty(hcAccountList)){
			for(int i = 0; i < hcAccountList.size(); i++){
				FmCntInfo fmCntInfo = new FmCntInfo();
				Map userMap = (Map)hcAccountList.get(i);
				Long id = (Long) userMap.get("id");
				Double pointNum = (Double) userMap.get("pointNum") == null ? 0 : (Double) userMap.get("pointNum");
				fmCntInfo.setCreateDate(nowTime);
				fmCntInfo.setUid(id.intValue());
				if(pointNum >= 0 && pointNum < 1000){
					onePhase.add(fmCntInfo);
				}
				if(pointNum >= 1000 && pointNum < 3000){
					twoPhase.add(fmCntInfo);
				}
				if(pointNum >= 3000 && pointNum < 6000){
					threePhase.add(fmCntInfo);
				}
				if(pointNum >= 6000 && pointNum < 10000){
					fourPhase.add(fmCntInfo);
				}
				if(pointNum >= 10000){
					fivePhase.add(fmCntInfo);
				}
			}
		}

		//计算权重
		int total = hcAccountList.size();
		DecimalFormat df=new DecimalFormat("0.00");

		//0~1000 用户一共得到当天20%, 分配60%R积分、40%向下稀释给飞羽更高高阶段的用户
		Double oneN = cntPool * Double.parseDouble(df.format((float)onePhase.size()/total));
		Double oneAllotN = oneN * 0.6;
		Double oneDilutN = oneN * 0.4;

		//1000~3000 用户一共得到当天10%, 分配70%R积分、30%向下稀释给飞羽更高高阶段的用户
		Double twoN = cntPool * Double.parseDouble(df.format((float)twoPhase.size()/total));
		Double twoAllotN = twoN * 0.7;
		Double twoDilutN = twoN * 0.3;

		//3000~6000 用户一共得到当天20%, 分配80%R积分、20%向下稀释给飞羽更高高阶段的用户
		Double threeN = cntPool * Double.parseDouble(df.format((float)threePhase.size()/total));
		Double threeAllotN = threeN * 0.8;
		Double threeDilutN = threeN * 0.2;

		//6000~10000 用户一共得到当天20%, 分配90%R积分、10%向下稀释给飞羽更高高阶段的用户
		Double fourN = cntPool * Double.parseDouble(df.format((float)fourPhase.size()/total));
		Double fourAllotN = fourN * 0.9;
		Double fourDilutN = fourN * 0.1;


		//10000以上用户一共得到当天20%
		Double fiveN = cntPool * Double.parseDouble(df.format((float)fivePhase.size()/total));

		AsyncManager.me().execute(AsyncCntFactory.excuteOnePhase(onePhase, oneAllotN, poolId, uid));

		AsyncManager.me().execute(AsyncCntFactory.excuteTwoPhase(twoPhase, twoAllotN, oneDilutN * 0.25, poolId, uid));

		AsyncManager.me().execute(AsyncCntFactory.excuteThreePhase(threePhase, threeAllotN, oneDilutN * 0.25, twoDilutN * 0.3, poolId, uid));

		AsyncManager.me().execute(AsyncCntFactory.excuteFourPhase(fourPhase, fourAllotN, oneDilutN * 0.25, twoDilutN * 0.3, threeDilutN * 0.5, poolId, uid));

		AsyncManager.me().execute(AsyncCntFactory.excuteFivePhase(fivePhase, fiveN, oneDilutN * 0.25, twoDilutN * 0.4, threeDilutN * 0.5, fourDilutN, poolId, uid));



	}

}
