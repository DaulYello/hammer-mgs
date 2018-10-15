package com.bm.fmkj.async;


import com.bm.fmkj.dao.FmIntegralInfo;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.service.FmIntegralInfoService;
import com.bm.fmkj.service.FmRpoolService;
import com.bm.fmkj.utils.SpringContextUtil;
import com.bm.fmkj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

/**
 * R积分异步执行任务工厂
 *
 * @author youxun
 */
public class AsyncIntegralFactory {

    /**
     * 执行0~1000
     *
     * @param oneR
     * @param onePhase
     * @param oneAllotR
     * @param currentYear
     */
    public static TimerTask excuteOnePhase(List<FmIntegralInfo> onePhase, Double oneAllotR, FmRpool fmRpool) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    //如果onePhase为空、则oneAllotR回收到R积分回收池
                    if (StringUtils.isEmpty(onePhase) && StringUtils.isNotNull(oneAllotR)){
                        //回收到R积分回收池
                        fmRpool.setRecycleNum(fmRpool.getRecycleNum() + oneAllotR);
                        fmRpool.setUpdatedate(new Date());
                        SpringContextUtil.getBean(FmRpoolService.class).updateRecycle(fmRpool);
                    }else{
                        //批量平均分配给用户
                        Double integralNum = oneAllotR / onePhase.size();
                        for(FmIntegralInfo info : onePhase){
                            info.setIntegralNum(integralNum);
                        }
                        SpringContextUtil.getBean(FmIntegralInfoService.class).allotRToUser(onePhase);
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
    public static TimerTask excuteTwoPhase(List<FmIntegralInfo> twoPhase, Double twoAllotR, Double oneDilutR, FmRpool fmRpool) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    //如果onePhase为空、则twoAllotR回收到R积分回收池
                    if (StringUtils.isEmpty(twoPhase)){
                        //回收到R积分回收池
                        fmRpool.setRecycleNum(fmRpool.getRecycleNum() + twoAllotR + oneDilutR);
                        fmRpool.setUpdatedate(new Date());
                        SpringContextUtil.getBean(FmRpoolService.class).updateRecycle(fmRpool);
                    }else{
                        //批量平均分配给用户
                        Double allotR = twoAllotR + oneDilutR;
                        Double integralNum = allotR / twoPhase.size();
                        for(FmIntegralInfo info : twoPhase){
                            info.setIntegralNum(integralNum);
                        }
                        SpringContextUtil.getBean(FmIntegralInfoService.class).allotRToUser(twoPhase);
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
    public static TimerTask excuteThreePhase(List<FmIntegralInfo> threePhase, Double threeAllotR, double oneDilutR, double twoDilutR, FmRpool fmRpool) {

        return new TimerTask() {
            @Override
            public void run() {
                try {

                    //如果onePhase为空、则threeAllotR回收到R积分回收池
                    if (StringUtils.isEmpty(threePhase)){
                        //回收到R积分回收池
                        fmRpool.setRecycleNum(fmRpool.getRecycleNum() + threeAllotR + oneDilutR + twoDilutR);
                        fmRpool.setUpdatedate(new Date());
                        SpringContextUtil.getBean(FmRpoolService.class).updateRecycle(fmRpool);
                    }else{
                        //批量平均分配给用户
                        Double allotR = threeAllotR + oneDilutR + twoDilutR;
                        Double integralNum = allotR / threePhase.size();
                        for(FmIntegralInfo info : threePhase){
                            info.setIntegralNum(integralNum);
                        }
                        SpringContextUtil.getBean(FmIntegralInfoService.class).allotRToUser(threePhase);
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
    public static TimerTask excuteFourPhase(List<FmIntegralInfo> fourPhase, Double fourAllotR, double oneDilutR, double twoDilutR, double threeDilutR, FmRpool fmRpool) {

        return new TimerTask() {
            @Override
            public void run() {
                try {

                    //如果onePhase为空、则fourAllotR回收到R积分回收池
                    if (StringUtils.isEmpty(fourPhase)){
                        //回收到R积分回收池
                        fmRpool.setRecycleNum(fmRpool.getRecycleNum() + fourAllotR + oneDilutR + twoDilutR + threeDilutR);
                        fmRpool.setUpdatedate(new Date());
                        SpringContextUtil.getBean(FmRpoolService.class).updateRecycle(fmRpool);
                    }else{
                        //批量平均分配给用户
                        Double allotR = fourAllotR + oneDilutR + twoDilutR + threeDilutR;
                        Double integralNum = allotR / fourPhase.size();
                        for(FmIntegralInfo info : fourPhase){
                            info.setIntegralNum(integralNum);
                        }
                        SpringContextUtil.getBean(FmIntegralInfoService.class).allotRToUser(fourPhase);
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
    public static TimerTask excuteFivePhase(List<FmIntegralInfo> fivePhase, Double fiveR, double oneDilutR, double twoDilutR, double threeDilutR, Double fourDilutR, FmRpool fmRpool) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    //如果onePhase为空、则fourAllotR回收到R积分回收池
                    if (StringUtils.isEmpty(fivePhase)){
                        //回收到R积分回收池
                        fmRpool.setRecycleNum(fmRpool.getRecycleNum() + fiveR + oneDilutR + twoDilutR + threeDilutR + fourDilutR);
                        fmRpool.setUpdatedate(new Date());
                        SpringContextUtil.getBean(FmRpoolService.class).updateRecycle(fmRpool);
                    }else{
                        //批量平均分配给用户
                        Double allotR = fiveR + oneDilutR + twoDilutR + threeDilutR;
                        Double integralNum = allotR / fivePhase.size();
                        for(FmIntegralInfo info : fivePhase){
                            info.setIntegralNum(integralNum);
                        }
                        SpringContextUtil.getBean(FmIntegralInfoService.class).allotRToUser(fivePhase);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
