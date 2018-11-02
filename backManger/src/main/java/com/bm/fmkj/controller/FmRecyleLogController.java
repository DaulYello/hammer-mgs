package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.service.FmRecyleLogService;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/FmRecyleLog")
public class FmRecyleLogController extends BaseController {
	@Autowired
	private FmRecyleLogService fmrecylelogService;

	private Logger LOGGER= LoggerFactory.getLogger(GcActivityController.class);

	@RequestMapping(value = "getFmRecyleLogs",method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<FmRecyleLog>> getFmRecyleLogs(@RequestParam HashMap<String , Object> param){

		LOGGER.info("getFmRecyleLogs-params={}", JSON.toJSONString(param));
		PageQuery pageQuery = new PageQuery();

		int pageNo = Integer.parseInt((String)param.get("pageNo"));
		int pageSize = Integer.parseInt((String)param.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(param);
		Pagenation<FmRecyleLog> pageResult = fmrecylelogService.getFmRecyleLogs(pageQuery);
		return new BaseResult<Pagenation<FmRecyleLog>>(BaseResultEnum.SUCCESS,pageResult);
	}

}