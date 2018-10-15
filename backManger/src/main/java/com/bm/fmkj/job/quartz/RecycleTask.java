package com.bm.fmkj.job.quartz;

import com.bm.fmkj.service.FmRpoolService;
import com.bm.fmkj.service.HcAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * R积分回收定时任务
 * 
 * @author youxun
 */
@Component("recycleTask")
public class RecycleTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecycleTask.class);

	@Autowired
	private HcAccountService hcaccountService;

	@Autowired
	private FmRpoolService fmRpoolService;

	public void runRecycle() {


	}

}
