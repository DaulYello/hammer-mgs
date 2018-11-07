package com.bm.fmkj.async;

import com.bm.fmkj.dao.SysOperateLog;
import com.bm.fmkj.service.SysOperateLogService;
import com.bm.fmkj.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import java.util.TimerTask;

public class AsyncBackLogFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncBackLogFactory.class);

    /**
     * 操作日志记录
     * @param operLog
     * @return
     */
    public static TimerTask recordOper(final SysOperateLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    SysOperateLogService operateLogService = SpringContextUtil.getBean(SysOperateLogService.class);
                    operateLogService.insterLog(operLog);
                } catch (BeansException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
