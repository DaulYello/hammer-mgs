package com.bm.fmkj.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bm.fmkj.dao.HcPointsRecord;
import com.bm.fmkj.service.HcPointsRecordService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/HcPointsRecord")
public class HcPointsRecordController extends BaseController {
	@Autowired
	private HcPointsRecordService hcpointsrecordService;

	/**
	 * 统计每个月的在线人数
	 * @return
	 */
	@RequestMapping(value="queryOnlineNumber",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView queryOnlineNum() {
		HashMap<String, Object> map= new HashMap<String, Object>();
		List<HcPointsRecord> list = hcpointsrecordService.queryOnlineNumber();
		map.put("onlines", list);
		return new ModelAndView("newLayout/myjsp/dynamic",map);
	}
}