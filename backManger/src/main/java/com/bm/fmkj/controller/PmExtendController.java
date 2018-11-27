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
import com.bm.fmkj.dao.PmExtend;
import com.bm.fmkj.service.PmExtendService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/PmExtend")
public class PmExtendController extends BaseController {
	@Autowired
	private PmExtendService pmextendService;


	private Logger LOGGER= LoggerFactory.getLogger(PmPromptController.class);

	/**
	 * 通过任务查询扩展字段信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="getExtendInfo",method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<PmExtend>> getExtendInfo(@RequestParam HashMap<String,Object> params) {

		LOGGER.info("查询扩展字段的参数getExtendInfo-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));

		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<PmExtend> pageResult = pmextendService.getExtendInfo(pageQuery);
		return new BaseResult<Pagenation<PmExtend>>(BaseResultEnum.SUCCESS,pageResult);
	}

	@RequestMapping(value="saveExtendInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Integer> saveExtendInfo(PmExtend extend) {
		LOGGER.info("保存扩展字段信息：saveExtendInfo-params={}", JSON.toJSONString(extend));
		if(StringUtils.isNull(extend.getTid())) {
			return new BaseResult<Integer>(BaseResultEnum.BLANK,-1);
		}

		int row = pmextendService.saveExtendInfo(extend);
		if(row > 0){
			return new BaseResult(BaseResultEnum.SUCCESS,extend.getId());
		}else{
			return new BaseResult(BaseResultEnum.ERROR,-1);
		}
	}

	@RequestMapping(value="deleteExtendInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> deleteExtendInfo(@RequestParam String id) {

		if(StringUtils.isNull(id)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		LOGGER.info("getPromptInfo-params={}", JSON.toJSONString(id));

		return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,pmextendService.deleteExtendInfo(id));
	}
}