package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.annotation.BackLog;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.constant.LogConstant;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmReleaseRule;
import com.bm.fmkj.service.FmReleaseRuleService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/FmReleaseRule")
public class FmReleaseRuleController extends BaseController {
	@Autowired
	private FmReleaseRuleService fmreleaseruleService;

	private Logger log= LoggerFactory.getLogger(GcActivityController.class);
	/**
	 * 查询积分规则 status
	 */
	@RequestMapping(value = "queryIntegralRule", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<FmReleaseRule>> queryIntegralRule(@RequestParam HashMap<String,Object> params) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			log.info("queryIntegralRule-params={}", JSON.toJSONString(params));
			PageQuery pageQuery = new PageQuery();
			int pageNo = Integer.parseInt((String)params.get("pageNo"));
			int pageSize = Integer.parseInt((String)params.get("pageSize"));
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			pageQuery.setParam(params);
			Pagenation<FmReleaseRule> pageResult = fmreleaseruleService.queryIntegralRule(pageQuery);
			return new BaseResult<Pagenation<FmReleaseRule>>(BaseResultEnum.SUCCESS,pageResult);
		} catch (Exception e) {
			throw new RuntimeException("查询积分规则失败：");
		}
	}

	/**
	 * 新增积分规则
	 * @param fmReleaseRule
	 * @return
	 */
	@BackLog(module = LogConstant.BACK_INTEGRAL,actionDesc = "新增积分规则")
	@RequestMapping(value="addIntegrateRule",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> addIntegrateRule(FmReleaseRule fmReleaseRule){
		log.info("addIntegrate-params={}", JSON.toJSONString(fmReleaseRule));
		if(StringUtils.isNull(fmReleaseRule)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = fmreleaseruleService.addIntegrateRule(fmReleaseRule);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}


	/**
	 * 修改积分规则
	 * @param fmReleaseRule
	 * @return
	 */
	@BackLog(module = LogConstant.BACK_INTEGRAL,actionDesc = "修改积分规则")
	@RequestMapping(value="editIntegralRule",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> editIntegralRule(FmReleaseRule fmReleaseRule){
		log.info("editIntegralRule-params={}", JSON.toJSONString(fmReleaseRule));
		if(StringUtils.isNull(fmReleaseRule)|| fmReleaseRule.getId() == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = fmreleaseruleService.editIntegralRule(fmReleaseRule);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);

	}


	/**
	 * 删除积分规则
	 * @param id
	 * @return
	 */
	@BackLog(module = LogConstant.BACK_INTEGRAL,actionDesc = "删除积分规则")
	@RequestMapping(value="dropIntegrationRule",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Boolean> dropIntegrationRule(@RequestParam String id){
		log.info("dropIntegrationRule -params={}", JSON.toJSONString(id));
		if(id == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		try {
			fmreleaseruleService.dropIntegrationRule(id);
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		} catch (Exception e) {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(), "删除异常:" + e.getMessage(), false);
		}
	}

}