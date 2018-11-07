package com.bm.fmkj.controller;

import java.util.HashMap;

import javax.json.Json;

import com.bm.fmkj.annotation.BackLog;
import com.bm.fmkj.constant.LogConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.abi.datatypes.Int;

import com.xl.utils.BaseController;
import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.HcNotices;
import com.bm.fmkj.service.HcNoticesService;

@Controller
@RequestMapping("/fmkj/HcNotices")
public class HcNoticesController extends BaseController {
	@Autowired
	private HcNoticesService hcnoticesService;

	private final static Logger log = LoggerFactory.getLogger(HcNoticesController.class);
	
	@RequestMapping(value="selectNotices",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<HcNotices>> selectNotices(@RequestParam HashMap<String, Object> param){
		
		log.info("selectNotices",JSON.toJSON(param));
		
		PageQuery pageQuery = new PageQuery();
		
		int pageNo = Integer.parseInt((String)param.get("pageNo"));
		int pageSize = Integer.parseInt((String)param.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(param);
		Pagenation<HcNotices> pagedResult = hcnoticesService.selectNotices(pageQuery);
		return new BaseResult<Pagenation<HcNotices>>(BaseResultEnum.SUCCESS, pagedResult);
	}

	@BackLog(module= LogConstant.BACK_NOTICE, actionDesc = "发布公告消息")
	@RequestMapping(value="publishNotice",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> savePublishNotice(@RequestParam HashMap<String,Object> param){
		
		log.info("publishNotice",JSON.toJSON(param));
		log.debug("发布消息---》"+(String)param.get("contents"));
		log.debug("发布消息title---》"+(String)param.get("title"));
		int result = hcnoticesService.savePublishNotice(param);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}

	@BackLog(module= LogConstant.BACK_NOTICE, actionDesc = "发布公告消息")
	@RequestMapping(value="deleteTicketById",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> deleteTicketById(@RequestParam HashMap<String, Object> param){
		log.info("deleteTicketById"+JSON.toJSONString(param));
		int result = hcnoticesService.deleteTicketById(param);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}

	@BackLog(module= LogConstant.BACK_NOTICE, actionDesc = "修改发布消息")
	@RequestMapping(value="editNotifySys",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> editNotifySys(@RequestParam HashMap<String,Object> param){
		
		log.info("publishNotice",JSON.toJSON(param));
		log.debug("修改发布消息---》"+(String)param.get("id"));
		log.debug("发布消息title---》"+(String)param.get("title"));
		int result = hcnoticesService.editNotifySys(param);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}
	
	
	
	
	
	
	
	
	
	
}