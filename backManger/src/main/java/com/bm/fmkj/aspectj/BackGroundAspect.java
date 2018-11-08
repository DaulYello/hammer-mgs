package com.bm.fmkj.aspectj;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.annotation.BackLog;
import com.bm.fmkj.async.AsyncBackLogFactory;
import com.bm.fmkj.async.AsyncBackLogManger;
import com.bm.fmkj.constant.LogConstant;
import com.bm.fmkj.dao.SysOperateLog;
import com.bm.fmkj.utils.StringUtils;
import com.xl.utils.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @function 自定义操作日志
 * @author hunagshuang
 * @date 2018-11-05
 */

@Aspect
@Component
@EnableAsync
public class BackGroundAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackGroundAspect.class);

    //配置切入点
    @Pointcut("@annotation(com.bm.fmkj.annotation.BackLog)")
    public void logPointCut(){}

    /**
     * 前置通知，用户拦截操作
     * @param joinPoint
     */
    @AfterReturning("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        LOGGER.info("前置通知，用户拦截操作---------------------------------------");
        handleLog(joinPoint,null, LogConstant.OPERATE_SUCCESS);
    }

    /**
     * 拦截异常操作
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()",throwing = "e")
    public void doAfter(JoinPoint joinPoint,Exception e){
        handleLog(joinPoint,e,LogConstant.OPERATE_FAIL);
    }

    @Async
    public void handleLog(final JoinPoint joinPoint,final Exception e, int operationState){

        try{
            BackLog actionLog = getAnnotationLog(joinPoint);
            if(StringUtils.isNull(actionLog)){
                return;
            }

            SysOperateLog operateLog = new SysOperateLog();
            operateLog.setCreateTime(new Date());

            String operateId = ServletUtils.getRequest().getHeader("uid");
            if(StringUtils.isNotEmpty(operateId)){
                operateLog.setUserId(Integer.parseInt(operateId));
            }

            if(StringUtils.isNotNull(e)){
                operateLog.setExceptionMsg(e.getMessage());
            }
            operateLog.setOperateStatus(Long.parseLong(operationState+""));
            operateLog.setRequestHost(ServletUtils.getRequest().getRemoteHost());
            operateLog.setRequestMethod(ServletUtils.getRequest().getRequestURI());
            Map<String, String[]> map = ServletUtils.getRequest().getParameterMap();
            operateLog.setRequestParams(JSON.toJSONString(map));

            operateLog.setModule(actionLog.module());
            operateLog.setOperateDesc(actionLog.actionDesc());
            operateLog.setRequestClass(joinPoint.getTarget().getClass().getName());
            LOGGER.info("S;LKDFPSDTKP"+joinPoint.getArgs());
            //异步保存数据库
            AsyncBackLogManger.me().execute(AsyncBackLogFactory.recordOper(operateLog));
        }catch(Exception exc){
            // 记录本地异常日志
            LOGGER.error("==前置通知异常==");
            LOGGER.error("异常信息:{}", exc.getMessage());
            exc.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private BackLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(BackLog.class);
        }
        return null;
    }
}
