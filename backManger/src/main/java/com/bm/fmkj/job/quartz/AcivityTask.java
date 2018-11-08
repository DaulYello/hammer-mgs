package com.bm.fmkj.job.quartz;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.service.GcActivityService;
import com.bm.fmkj.service.GcJoinactivityrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author:shuaghuang
 * @createtime 2018-10-25
 * @explanation 每天凌晨12:30点跑定时任务，统计前一天活动产生的手续费，将其x%释放，其余的保存到公司账户
 */
@Component("acivityTask")
public class AcivityTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoundageTact.class);

    @Autowired
    private GcActivityService gcActivityService;
    @Autowired
    private GcJoinactivityrecordService joinactivityrecordService;
    @Autowired
    private HcAccountMapper hcAccountMapper;
    @Autowired
    private FmRecyleLogMapper fmRecyleLogMapper;
    @Autowired
    private GcActivityMapper gcactivityMapper;


    public void runAcivity(){

        gcActivityService.acitivtyFailBackCNT();

        //List<GcActivity> activities = gcActivityService.queryActivityByStatus();

        /*for(GcActivity gcActivity : activities){
            Date now = new Date();
            if(gcActivity.getEndtime().getTime() - now.getTime()<=0 ){
                LOGGER.info("活动名称为："+gcActivity.getPname()+"活动已过期，id号位："+gcActivity.getId());
                List<GcJoinactivityrecord> joinactivityrecords=joinactivityrecordService.getGcJoinactivityrecords(gcActivity.getId());
                LOGGER.info("活动名称为："+gcActivity.getPname()+"活动参与记录的人数"+joinactivityrecords.size());
                if(joinactivityrecords.size()<=0){
                    LOGGER.info("活动名称为："+gcActivity.getPname()+"活动已经到期也没有人参加这个活动");
                    return;
                }
                List<FmRecyleLog> recyleLogs = new ArrayList<>();
                for(GcJoinactivityrecord gcJoinactivityrecord : joinactivityrecords){
                    HcAccount account = hcAccountMapper.selectByPrimaryKey(gcJoinactivityrecord.getUid());
                    double cnt = 0.0;
                    if(null == account.getCnt()){
                        account.setCnt(gcActivity.getPar());
                    }else{
                        account.setCnt(account.getCnt()+gcActivity.getPar());
                    }
                    boolean result = hcAccountMapper.updateByPrimaryKeySelective(account) > 0 ? true : false;
                    if(!result){
                        LOGGER.info("活动名称为："+gcActivity.getPname()+"活动已经到期也没有人参加这个活动");
                        throw new RuntimeException("更新用户的cnt时报错！事务回滚!");
                    }
                    LOGGER.debug("记录退还给用户的cnt，用户id="+account.getId());
                    FmRecyleLog recyleLog = new FmRecyleLog();
                    recyleLog.setUid(account.getId());
                    recyleLog.setFriendId(account.getId());
                    recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
                    recyleLog.setTakeDate(new Date());
                    recyleLog.setTakeNum(gcActivity.getPar());
                    recyleLog.setTakeType(TakeEnum.FAIL_BACK.status);
                    recyleLog.setTakeMsg("活动流局，活动【"+gcActivity.getId()+"】退还用户"+ gcActivity.getPar() + "CNT");
                    recyleLogs.add(recyleLog);
                }
                fmRecyleLogMapper.batchAddRecyleLog(recyleLogs);
                LOGGER.debug("将活动设为失败状态5");
                gcActivity.setStatus(5);
                gcActivity.setEndtime(new Date());
                boolean result = gcactivityMapper.updateByPrimaryKeySelective(gcActivity)>0 ? true : false;
                if(!result){
                    LOGGER.info("更新活动状态为5失败，活动id:"+gcActivity.getId());
                    throw new RuntimeException("更新用户的cnt时报错！事务回滚!");
                }
            }
        }*/

    }
}
