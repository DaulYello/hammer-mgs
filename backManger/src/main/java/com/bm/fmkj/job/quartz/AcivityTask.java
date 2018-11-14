package com.bm.fmkj.job.quartz;

import com.bm.fmkj.service.GcActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:shuaghuang
 * @createtime 2018-10-25
 * @explanation 每天凌晨12:30点跑定时任务，统计前一天活动产生的手续费，将其x%释放，其余的保存到公司账户
 */
@Component("acivityTask")
public class AcivityTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoundageTact.class);

    @Autowired
    private GcActivityService gcactivityService;



    public void runAcivity(){

        gcactivityService.acitivtyFailBackCNT();

    }
}
