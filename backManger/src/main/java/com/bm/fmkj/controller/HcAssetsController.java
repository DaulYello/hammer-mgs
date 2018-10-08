package com.bm.fmkj.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bm.fmkj.dao.HcAssets;
import com.bm.fmkj.service.HcAssetsService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/HcAssets")
public class HcAssetsController extends BaseController {
	@Autowired
	private HcAssetsService hcassetsService;

	/**
	 * 保存用户的资产信息
	 * @param assets
	 * @return
	 */
	@RequestMapping(value="saveAssetsContent",method = RequestMethod.POST)
	@ResponseBody
	public boolean saveAssetsContent(HcAssets assets) {
		return hcassetsService.saveAssetsContent(assets);
	}
	
	/**
	 * 通过用户条件，查询用户的资产记录
	 * @param ph
	 * @return
	 */
	@RequestMapping(value="selectAssetsContent",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> selectAssetsContent(HcAssets ph) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HcAssets> list = hcassetsService.selectAssetsContent(ph);
		map.put("hcAssets", list);
		return map;
	}
}