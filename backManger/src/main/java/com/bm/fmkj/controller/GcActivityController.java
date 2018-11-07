package com.bm.fmkj.controller;

import java.util.HashMap;

import com.bm.fmkj.annotation.BackLog;
import com.bm.fmkj.constant.LogConstant;
import com.bm.fmkj.dao.GcJoinactivityrecord;
import com.bm.fmkj.service.GcJoinactivityrecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.GcActivity;
import com.bm.fmkj.service.GcActivityService;
import com.xl.utils.BaseController;
import org.web3j.crypto.Hash;


@Controller
@RequestMapping("/fmkj/GcActivity")
public class GcActivityController extends BaseController {
	@Autowired
	private GcActivityService gcactivityService;

	private Logger log= LoggerFactory.getLogger(GcActivityController.class);

	@Autowired
	private GcJoinactivityrecordService joinactivityrecordService;
	/**
	 * 活动分页查询 status
	 */
	@RequestMapping(value = "queryAllActivityByPage", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<GcActivity>> queryAllActivityByPage(@RequestParam HashMap<String,Object> params) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			log.info("queryUserInfo-params={}", JSON.toJSONString(params));
			PageQuery pageQuery = new PageQuery();
			int pageNo = Integer.parseInt((String)params.get("pageNo"));
			int pageSize = Integer.parseInt((String)params.get("pageSize"));
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			pageQuery.setParam(params);
			Pagenation<GcActivity> pageResult = gcactivityService.queryAllActivityByPage(pageQuery);
			return new BaseResult<Pagenation<GcActivity>>(BaseResultEnum.SUCCESS,pageResult);
		} catch (Exception e) {
			throw new RuntimeException("查询活动失败：");
		}
	}

	/**
	 * 通过id及传入参数修改活动数据 status状态-- 1.通过上线 2.強制下线 3.不通过
	 */
	@BackLog(module= LogConstant.BACK_HAMMER, actionDesc = "审核活动")
	@RequestMapping(value = "auditActivity", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<HashMap<String,Object>> auditActivity(@RequestParam HashMap<String,Object> params) {
		try {
			log.debug("进入接口auditActivity");
			HashMap<String,Object> map=gcactivityService.updateActivityAndContract(params);
			if(map.get("status").equals(false)) {
				return new BaseResult<HashMap<String, Object>>(BaseResultEnum.ERROR, map);
			}
			return new BaseResult<HashMap<String, Object>>(BaseResultEnum.SUCCESS, map);
		} catch (Exception e) {
			throw new RuntimeException("审核失败！");
		}
	}
	/**
	 * 活动进入参与状态、竟锤状态以及获取优胜者
	 * @param params
	 * @return
	 */
	@BackLog(module= LogConstant.BACK_HAMMER, actionDesc = "活动进入参与状态、竟锤状态以及获取优胜者")
	@RequestMapping(value = "updatepuzzleHammerState", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<HashMap<String,Object>> updatepuzzleHammerState(@RequestParam HashMap<String, Object> params) {

		log.debug("进入接口修改合约状态的-》updatepuzzleHammerState");
		int aid=Integer.parseInt((String) params.get("id"));
		HashMap<String,Object> hashMap= gcactivityService.updatepuzzleHammerStatus(params);
		if (hashMap.get("status").equals(false)){
			return new BaseResult<HashMap<String, Object>>(BaseResultEnum.ERROR, hashMap);
		}
		log.debug("活动正常结束，给参加活动没有重锤的人发放R积分作为奖励。");
		HashMap<String,Object> map= joinactivityrecordService.grantCredits(aid);
		if (map.get("status").equals(false)){
			return new BaseResult<HashMap<String, Object>>(BaseResultEnum.ERROR, map);
		}
		return new BaseResult<HashMap<String, Object>>(BaseResultEnum.SUCCESS, map);
	}
	
	/**
	 * 取消活动
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "cancelActivity", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<HashMap<String,Object>> cancelActivity(@RequestParam HashMap<String, Object> params) {
		try {
			log.debug("进入--》cancelActivity");
			HashMap<String,Object> map = gcactivityService.cancelActivity(params);
			if(map.get("status").equals(false)) {
				return new BaseResult<HashMap<String,Object>>(BaseResultEnum.SUCCESS, map);
			}
			return new BaseResult<HashMap<String,Object>>(BaseResultEnum.SUCCESS, map);
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 取消活动
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "endActivity", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> endActivity(@RequestParam HashMap<String, Object> params) {
		try {
			log.debug("进入--》endActivity");
			boolean result = gcactivityService.endActivity(params);
			if(result) {
				return new BaseResult<Boolean>(BaseResultEnum.SUCCESS, result);
			}
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS, result);
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}