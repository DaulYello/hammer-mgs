package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.PmImage;
import com.bm.fmkj.dao.PmStrategy;
import com.bm.fmkj.dao.PmTask;
import com.bm.fmkj.domain.PmPartDto;
import com.bm.fmkj.domain.PmStrategyDto;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmPart;
import com.bm.fmkj.service.PmPartService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/fmkj/part")
public class PmPartController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PmPartController.class);

	@Autowired
	private PmPartService pmpartService;

	/**
	 * 列表
	 */
	@RequestMapping(value="getPartPage",method= RequestMethod.GET)
	@ResponseBody
	public BaseResult getPartPage(@RequestParam HashMap<String, Object> params){
		LOGGER.info("getPartPage-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<PmPartDto> pagedResult = pmpartService.getPartPage(pageQuery);
		return new BaseResult(BaseResultEnum.SUCCESS,pagedResult);
	}


	/**
	 * 审核
	 */
	@RequestMapping(value="auditPart",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> auditPart(PmPart pmPart){
		LOGGER.info("auditPart-params={}", JSON.toJSONString(pmPart));
		if(StringUtils.isNull(pmPart)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = pmpartService.auditPart(pmPart);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}

}