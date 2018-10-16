package com.bm.fmkj.async;


import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.FmCntInfo;
import com.bm.fmkj.dao.FmIntegralInfo;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.service.FmCntInfoService;
import com.bm.fmkj.service.FmIntegralInfoService;
import com.bm.fmkj.service.FmRpoolService;
import com.bm.fmkj.utils.SpringContextUtil;
import com.bm.fmkj.utils.StringUtils;
import com.xl.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * 回收异步执行任务工厂
 *
 * @author youxun
 */
public class AsyncRecyleFactory {

    public static TimerTask recyleCNT(int uid) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    List<FmCntInfo> fmCntInfoList = SpringContextUtil.getBean(FmCntInfoService.class).queryRecyleCNT();
                    if(StringUtils.isEmpty(fmCntInfoList)){
                        return;
                    }
                    //一共回收多少
                    Double totalNum = 0D;
                    //记录详细回收日志
                    List<FmRecyleLog> recyleLogs = new ArrayList<>();
                    for(FmCntInfo info : fmCntInfoList){
                        FmRecyleLog recyleLog = new FmRecyleLog();
                        recyleLog.setUid(info.getUid());
                        recyleLog.setTakeNum(info.getCntNum());
                        recyleLog.setTakeDate(new Date());
                        recyleLog.setTakeType(TakeEnum.TYPE_TASK.status);
                        recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
                        recyleLogs.add(recyleLog);
                        totalNum = totalNum + info.getCntNum();
                    }
                    SpringContextUtil.getBean(FmCntInfoService.class).recyleCNT(uid, totalNum, recyleLogs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static TimerTask recyleR() {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    //查询当前时间48小时前没有被收取的R积分
                    List<FmIntegralInfo> integralInfoList = SpringContextUtil.getBean(FmIntegralInfoService.class).queryRBefore();
                    if(StringUtils.isEmpty(integralInfoList)){
                        return;
                    }
                    //一共回收多少
                    Double totalNum = 0D;
                    List<FmRecyleLog> recyleLogs = new ArrayList<>();
                    for(FmIntegralInfo info : integralInfoList){
                        FmRecyleLog recyleLog = new FmRecyleLog();
                        recyleLog.setUid(info.getUid());
                        recyleLog.setTakeNum(info.getIntegralNum());
                        recyleLog.setTakeDate(new Date());
                        recyleLog.setTakeType(TakeEnum.TYPE_TASK.status);
                        recyleLog.setRecyleType(RecyleEnum.TYPE_R.status);
                        recyleLogs.add(recyleLog);
                        totalNum = totalNum + info.getIntegralNum();
                    }
                    FmRpool fmRpool  = SpringContextUtil.getBean(FmRpoolService.class).queryRpoolByYear(DateUtil.getSysYear());
                    if(StringUtils.isNull(fmRpool)){
                        return;
                    }
                    fmRpool.setRecycleNum(fmRpool.getRecycleNum() + totalNum);
                    fmRpool.setUpdatedate(new Date());
                    SpringContextUtil.getBean(FmRpoolService.class).recyleR(fmRpool, recyleLogs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
