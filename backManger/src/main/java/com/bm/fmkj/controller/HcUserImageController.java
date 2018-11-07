package com.bm.fmkj.controller;

import java.util.HashMap;

import com.bm.fmkj.annotation.BackLog;
import com.bm.fmkj.constant.LogConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.HcUserImage;
import com.bm.fmkj.service.HcUserImageService;
import com.xl.utils.BaseController;

@Controller
@ResponseBody
@RequestMapping("/fmkj/HcUserImage")
public class HcUserImageController extends BaseController {
	@Autowired
	private HcUserImageService hcuserimageService;
	
	
	private static final Logger log = LoggerFactory.getLogger(HcUserImage.class);

	@RequestMapping(value="getUserRealInfo",method=RequestMethod.GET)
	public BaseResult<Pagenation<HcUserImage>> getUserRealInfo(@RequestParam HashMap<String, Object> params){
		log.debug("查询用户实名认证的信息参数："+JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageNo(Integer.parseInt((String)params.get("pageNo")));
		pageQuery.setPageSize(Integer.parseInt((String)params.get("pageSize")));
		pageQuery.setParam(params);
		Pagenation<HcUserImage> pagedResult = hcuserimageService.getUserRealInfo(pageQuery);
		return new BaseResult<Pagenation<HcUserImage>>(BaseResultEnum.SUCCESS,pagedResult);
	}
	
	@RequestMapping(value="approveUserRealInfo",method=RequestMethod.POST)
	@BackLog(module= LogConstant.BACK_USER, actionDesc = "实名认证审核操作")
	public BaseResult<HashMap<String,Object>> approveUserRealInfo(@RequestParam HashMap<String,Object> param){
		log.debug("实名认证审核通过，flag=1表示审核通过，id："+JSON.toJSONString(param));
		HashMap<String,Object> map = hcuserimageService.approveUserRealInfo(param);
		if(map.get("status").equals(false)) {
			return new BaseResult<HashMap<String,Object>>(BaseResultEnum.ERROR, map);
		}
		return new BaseResult<HashMap<String,Object>>(BaseResultEnum.SUCCESS, map);
	}
}