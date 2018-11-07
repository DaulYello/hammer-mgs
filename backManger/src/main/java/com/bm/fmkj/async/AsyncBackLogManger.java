package com.bm.fmkj.async;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncBackLogManger {

    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

    /**
     * 单例模式
     */
    private static AsyncBackLogManger me = new AsyncBackLogManger();

    public static AsyncBackLogManger me() {
        return me;
    }

    /**
     * 执行任务
     *
     * @param
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
