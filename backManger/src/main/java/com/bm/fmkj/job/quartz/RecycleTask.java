package com.bm.fmkj.job.quartz;

import com.bm.fmkj.async.AsyncIntegralFactory;
import com.bm.fmkj.async.AsyncManager;
import com.bm.fmkj.async.AsyncRecyleFactory;
import com.bm.fmkj.dao.FmCntInfo;
import com.bm.fmkj.service.FmCntInfoService;
import com.bm.fmkj.service.HcAccountService;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * R积分回收定时任务
 * 
 * @author youxun
 */
@Component("recycleTask")
public class RecycleTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecycleTask.class);

	public void runRecyle(String param) {
		if(StringUtils.isEmpty(param)){
			LOGGER.info("runRecyle公司账户ID不能为空");
			return;
		}
		int uid = Integer.parseInt(param);
		//1、回收未收取的CNT到公司账户
		AsyncManager.me().execute(AsyncRecyleFactory.recyleCNT(uid));

		//2、回收48小时未收取的R积分
		AsyncManager.me().execute(AsyncRecyleFactory.recyleR(uid));


	}

}
