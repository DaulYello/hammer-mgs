package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmPrompt;
import com.bm.fmkj.service.PmPromptService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/PmPrompt")
public class PmPromptController extends BaseController {
	@Autowired
	private PmPromptService pmpromptService;

	private Logger LOGGER= LoggerFactory.getLogger(PmPromptController.class);

	/**
	 * 通过任务查询温馨提示信息
	 * @param params
	 * @return
	 */
	//@BackLog(module= LogConstant.BACK_USER, actionDesc = "用户登录")
	@RequestMapping(value="getPromptInfo",method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<PmPrompt>> getPromptInfo(@RequestParam HashMap<String,Object> params) {

		LOGGER.info("getPromptInfo-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));

		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<PmPrompt> pageResult = pmpromptService.getPromptInfo(pageQuery);
		return new BaseResult<Pagenation<PmPrompt>>(BaseResultEnum.SUCCESS,pageResult);
	}

	@RequestMapping(value="savePromptInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> savePromptInfo(PmPrompt prompt) {

		if(StringUtils.isNull(prompt)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		if(StringUtils.isNull(prompt.getTid())) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		if(StringUtils.isNull(prompt.getPromptText())) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		LOGGER.info("getPromptInfo-params={}", JSON.toJSONString(prompt));

		return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,pmpromptService.savePromptInfo(prompt));
	}

	@RequestMapping(value="deletePromptInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> deletePromptInfo(@RequestParam String id) {

		if(StringUtils.isNull(id)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		LOGGER.info("getPromptInfo-params={}", JSON.toJSONString(id));

		return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,pmpromptService.deletePromptInfo(id));
	}

}