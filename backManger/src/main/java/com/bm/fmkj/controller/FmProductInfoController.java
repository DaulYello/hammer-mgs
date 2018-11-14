package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.domain.ProductDto;
import com.xl.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmProductInfo;
import com.bm.fmkj.service.FmProductInfoService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/FmProductInfo")
public class FmProductInfoController extends BaseController {
	@Autowired
	private FmProductInfoService fmproductinfoService;

	private Logger LOGGER= LoggerFactory.getLogger(GcActivityController.class);
	/**
	 * 分页查询商品列表
	 */
	@RequestMapping(value = "queryGoodsList", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<ProductDto>> queryGoodsList(@RequestParam HashMap<String,Object> params) throws ParseException{
		try {
			LOGGER.info("queryGoodsList-params={}", JSON.toJSONString(params));
			PageQuery pageQuery = new PageQuery();
			int pageNo = Integer.parseInt((String)params.get("pageNo"));
			int pageSize = Integer.parseInt((String)params.get("pageSize"));
			String starttime=(String)params.get("starttime");
			String endtime=(String)params.get("endtime");
			if(null != starttime && null != endtime){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startDateStr = DateUtil.parseHour(starttime);
				String endDateStr = DateUtil.parseHour(endtime);
				if(sdf.parse(startDateStr).getTime()>sdf.parse(endDateStr).getTime()){
					return new BaseResult(BaseResultEnum.ERROR,"开始时间不能大于结束时间！");
				}
				params.put("endtime",endDateStr);
				params.put("starttime",startDateStr);
			}
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			pageQuery.setParam(params);
			Pagenation<ProductDto> pageResult = fmproductinfoService.queryGoodsList(pageQuery);
			return new BaseResult<Pagenation<ProductDto>>(BaseResultEnum.SUCCESS,pageResult);
		} catch (Exception e) {
			throw new RuntimeException("查询商品列表失败：");
		}
	}

}