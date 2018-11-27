package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.PmStrategy;
import com.bm.fmkj.dao.PmTask;
import com.bm.fmkj.domain.PmStrategyDto;
import com.bm.fmkj.service.PmStrategyService;
import com.bm.fmkj.utils.StringUtils;
import com.xl.utils.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/fmkj/strategy")
public class PmStrategyController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PmStrategyController.class);

	@Autowired
	private PmStrategyService pmstrategyService;

	/**
	 * 新增
	 */
	@RequestMapping(value="addStrategy",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> addStrategy(PmStrategy strategy){
		LOGGER.info("addStrategy-params={}", JSON.toJSONString(strategy));
		if(StringUtils.isNull(strategy)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = pmstrategyService.addStrategy(strategy);

		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="deleteStrategy",method= RequestMethod.GET)
	@ResponseBody
	public BaseResult<Boolean> deleteStrategy(@RequestParam String ids){
		LOGGER.info("deleteStrategy-params={}", JSON.toJSONString(ids));
		if(StringUtils.isEmpty(ids)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK.getStatus(), "参数为空", false);
		}
		try {
			int result = pmstrategyService.deleteStrategy(ids);
			if(result > 0) {
				return new BaseResult<Boolean>(BaseResultEnum.SUCCESS.getStatus(), "删除成功", true);
			}
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(),"删除失败", false);
		} catch (Exception e) {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(), "删除异常:" + e.getMessage(), false);
		}
	}

	/**
	 * 列表
	 */
	@RequestMapping(value="getStrategyPage",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult getStrategyPage(@RequestParam HashMap<String, Object> params){
		LOGGER.info("getStrategyPage-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<PmStrategyDto> pagedResult = pmstrategyService.getStrategyPage(pageQuery);
		List<PmTask> pmTasks = pmstrategyService.getTaskList(params);
		HashMap<String, Object> result = new HashMap<>();
		result.put("strategy", pagedResult);
		result.put("task", pmTasks);
		return new BaseResult(BaseResultEnum.SUCCESS,result);
	}

}