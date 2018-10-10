package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.GcActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.service.FmRpoolService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/FmRpool")
public class FmRpoolController extends BaseController {
	@Autowired
	private FmRpoolService fmrpoolService;

	private Logger log= LoggerFactory.getLogger(GcActivityController.class);
	/**
	 * 活动分页查询 status
	 */
	@RequestMapping(value = "queryIntegral", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<FmRpool>> queryIntegral(@RequestParam HashMap<String,Object> params) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			log.info("queryIntegral-params={}", JSON.toJSONString(params));
			PageQuery pageQuery = new PageQuery();
			int pageNo = Integer.parseInt((String)params.get("pageNo"));
			int pageSize = Integer.parseInt((String)params.get("pageSize"));
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			pageQuery.setParam(params);
			Pagenation<FmRpool> pageResult = fmrpoolService.queryIntegrals(pageQuery);
			return new BaseResult<Pagenation<FmRpool>>(BaseResultEnum.SUCCESS,pageResult);
		} catch (Exception e) {
			throw new RuntimeException("查询活动失败：");
		}
	}
}