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
import com.bm.fmkj.dao.HcApkversion;
import com.bm.fmkj.service.HcApkversionService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/HcApkversion")
public class HcApkversionController extends BaseController {
	@Autowired
	private HcApkversionService hcapkversionService;

	private Logger LOGGER= LoggerFactory.getLogger(HcApkversionController.class);

	/**
	 * 查询任务列表
	 * @param params
	 * @return
	 */
	@RequestMapping(value="getVersionPage",method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<HcApkversion>> getVersionPage(@RequestParam HashMap<String,Object> params) {

		LOGGER.info("queryGoodsList-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));

		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<HcApkversion> pageResult = hcapkversionService.getVersionPage(pageQuery);
		return new BaseResult<Pagenation<HcApkversion>>(BaseResultEnum.SUCCESS,pageResult);
	}

/**
	 * 新增任务
	 */

	@RequestMapping(value="addVersion",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> addVersion(HcApkversion apkversion){
		LOGGER.info("addVersion-params={}", JSON.toJSONString(apkversion));
		if(StringUtils.isNull(apkversion)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = hcapkversionService.addVersion(apkversion);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}
	/**
	 * 修改任务
	 */

	@RequestMapping(value="updateVersion",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> updateTask(HcApkversion apkversion){
		LOGGER.info("updateVersion-params={}", JSON.toJSONString(apkversion));
		if(StringUtils.isNull(apkversion)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = hcapkversionService.updateVersion(apkversion);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}


	/**
	 * 删除版本信息
	 */

	@RequestMapping(value="deleteVersion",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Boolean> deleteVersion(@RequestParam Integer id){
		LOGGER.info("updateVersion-params={}", JSON.toJSONString(id));
		if(StringUtils.isNull(id)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = hcapkversionService.deleteVersion(id);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}

}