package com.bm.fmkj.async;


import com.bm.fmkj.dao.FmCntInfo;
import com.bm.fmkj.dao.FmIntegralInfo;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.service.FmCntInfoService;
import com.bm.fmkj.service.FmIntegralInfoService;
import com.bm.fmkj.service.FmRpoolService;
import com.bm.fmkj.utils.SpringContextUtil;
import com.bm.fmkj.utils.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * CNT异步执行任务工厂
 *
 * @author youxun
 */
public class AsyncCntFactory {

    /**
     * 执行0~1000
     *
     * @param oneR
     * @param onePhase
     * @param oneAllotR
     * @param currentYear
     */
    public static TimerTask excuteOnePhase(List<FmCntInfo> onePhase, Double oneAllotN, int poolId, int uid) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    //如果onePhase为空、则oneAllotR回收到公司CNT账户
                    if (StringUtils.isEmpty(onePhase) && StringUtils.isNotNull(oneAllotN)){
                        //回收到公司CNT账户
                        SpringContextUtil.getBean(FmCntInfoService.class).recyleToAccount(oneAllotN, poolId, uid);
                    }else{
                        //批量平均分配给用户
                        Double cntNum = oneAllotN / onePhase.size();
                        for(FmCntInfo info : onePhase){
                            info.setCntNum(cntNum);
                        }
                        SpringContextUtil.getBean(FmCntInfoService.class).allotCNToUser(onePhase);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }


    /**
     * 执行1000~3000
     * @param twoPhase
     * @param twoAllotR
     * @param oneDilutR
     * @param fmRpool
     * @return
     */
    public static TimerTask excuteTwoPhase(List<FmCntInfo> twoPhase, Double twoAllotN, Double oneDilutN, int poolId, int uid) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    Double allotN = twoAllotN + oneDilutN;
                    //如果onePhase为空、则oneAllotR回收到公司CNT账户
                    if (StringUtils.isEmpty(twoPhase)){
                        //回收到公司CNT账户
                        SpringContextUtil.getBean(FmCntInfoService.class).recyleToAccount(allotN, poolId, uid);
                    }else{
                        //批量平均分配给用户
                        Double cntNum = allotN / twoPhase.size();
                        for(FmCntInfo info : twoPhase){
                            info.setCntNum(cntNum);
                        }
                        SpringContextUtil.getBean(FmCntInfoService.class).allotCNToUser(twoPhase);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 执行3000~6000
     * @param threePhase
     * @param threeAllotR
     * @param v
     * @param v1
     * @param fmRpool
     * @return
     */
    public static TimerTask excuteThreePhase(List<FmCntInfo> threePhase, Double threeAllotN, double oneDilutN, double twoDilutN, int poolId, int uid) {

        return new TimerTask() {
            @Override
            public void run() {
                try {
                    Double allotN = threeAllotN + oneDilutN + twoDilutN;
                    //如果onePhase为空、则oneAllotR回收到公司CNT账户
                    if (StringUtils.isEmpty(threePhase)){
                        //回收到公司CNT账户
                        SpringContextUtil.getBean(FmCntInfoService.class).recyleToAccount(allotN, poolId, uid);
                    }else{
                        //批量平均分配给用户
                        Double cntNum = allotN / threePhase.size();
                        for(FmCntInfo info : threePhase){
                            info.setCntNum(cntNum);
                        }
                        SpringContextUtil.getBean(FmCntInfoService.class).allotCNToUser(threePhase);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

    }

    /**
     * 执行6000~10000
     * @param fourPhase
     * @param fourAllotR
     * @param v
     * @param v1
     * @param v2
     * @param fmRpool
     * @return
     */
    public static TimerTask excuteFourPhase(List<FmCntInfo> fourPhase, Double fourAllotN, double oneDilutN, double twoDilutN, double threeDilutN, int poolId, int uid) {

        return new TimerTask() {
            @Override
            public void run() {
                try {
                    Double allotN = fourAllotN + oneDilutN + twoDilutN + threeDilutN;
                    //如果onePhase为空、则oneAllotR回收到公司CNT账户
                    if (StringUtils.isEmpty(fourPhase)){
                        //回收到公司CNT账户
                        SpringContextUtil.getBean(FmCntInfoService.class).recyleToAccount(allotN, poolId, uid);
                    }else{
                        //批量平均分配给用户
                        Double cntNum = allotN / fourPhase.size();
                        for(FmCntInfo info : fourPhase){
                            info.setCntNum(cntNum);
                        }
                        SpringContextUtil.getBean(FmCntInfoService.class).allotCNToUser(fourPhase);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 执行10000以上
     * @param fivePhase
     * @param fiveR
     * @param oneDilutR
     * @param twoDilutR
     * @param threeDilutR
     * @param fourDilutR
     * @param fmRpool
     * @return
     */
    public static TimerTask excuteFivePhase(List<FmCntInfo> fivePhase, Double fiveN, double oneDilutN, double twoDilutN, double threeDilutN, Double fourDilutN, int poolId, int uid) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    Double allotN = fiveN + oneDilutN + twoDilutN + threeDilutN + fourDilutN;
                    //如果onePhase为空、则oneAllotR回收到公司CNT账户
                    if (StringUtils.isEmpty(fivePhase)){
                        //回收到公司CNT账户
                        SpringContextUtil.getBean(FmCntInfoService.class).recyleToAccount(allotN, poolId, uid);
                    }else{
                        //批量平均分配给用户
                        Double cntNum = allotN / fivePhase.size();
                        for(FmCntInfo info : fivePhase){
                            info.setCntNum(cntNum);
                        }
                        SpringContextUtil.getBean(FmCntInfoService.class).allotCNToUser(fivePhase);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
