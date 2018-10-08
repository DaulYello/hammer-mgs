package com.bm.fmkj.job.quartz;

import org.springframework.stereotype.Component;

/**
 * 定时任务调度
 * 
 * @author youxun
 */
@Component("raceTask")
public class RaceTask {

	public void runParams(String params) {
		System.out.println("执行有参方法：" + params);
	}

	public void runNoParams() {
		System.out.println("执行无参方法");
	}

}
