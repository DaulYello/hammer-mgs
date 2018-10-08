package com.bm.fmkj.controller;

import java.util.HashMap;

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
import com.bm.fmkj.dao.GcTicket;
import com.bm.fmkj.service.GcTicketService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcTicket")
public class GcTicketController extends BaseController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(GcTicketController.class);

	@Autowired
	private GcTicketService gcticketService;
	
	/**
	 * 传入实体id查询实体信息
	 */
	@RequestMapping(value="selectTicketById")
	@ResponseBody
	public HashMap<String, Object> selectTicketById(GcTicket gt){
		HashMap<String, Object> map = new HashMap<String, Object>();
		GcTicket ticket = gcticketService.selectTicketById(gt);
		if(ticket==null) {
			map.put("ticket", null);
			return map;
		}
		map.put("ticket", ticket);
		return map;
	}
	
	/**
	 * 分页查询所有票种
	 * @return
	 */
	@RequestMapping(value="selectAllTicket", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<GcTicket>> selectAllTicket(@RequestParam HashMap<String, Object> params) {
		LOGGER.info("selectAllTicket-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<GcTicket>  pagedResult = gcticketService.selectAllTicket(pageQuery);
        return new BaseResult<Pagenation<GcTicket>>(BaseResultEnum.SUCCESS,pagedResult);
	}
	
	/**
	 * 新建门票票种
	 */
	@RequestMapping(value="buildNewTicket",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> buildNewTicket(GcTicket gt){
		LOGGER.info("buildNewTicket-params={}", JSON.toJSONString(gt));
		boolean result = gcticketService.insertTicketByExample(gt);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR,result);
	}
	
	/**
	 * 传入实体id删除票种
	 */
	@RequestMapping(value="deleteTicketById",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Boolean> deleteTicketById(@RequestParam Integer id){
		LOGGER.info("deleteTicketById-params={}", JSON.toJSONString(id));
		if(id == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,null);
		}
		GcTicket gt = new GcTicket();
		gt.setId(id);
		boolean result = gcticketService.deleteTicketById(gt);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR,result);
	}
	
	/**
	 * 传入实体属性根据实体id修改数据信息
	 */
	@RequestMapping(value="updateTicketById",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> updateTicketById(GcTicket gt){
		LOGGER.info("updateTicketById-params={}", JSON.toJSONString(gt));
		if(gt == null || gt.getId() == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,null);
		}
		boolean result = gcticketService.updateTicketById(gt);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR,result);
	}

}