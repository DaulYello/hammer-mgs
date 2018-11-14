package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmOrderInfo;
import com.bm.fmkj.service.FmOrderInfoService;

@Controller
@RequestMapping("/fmkj/FmOrderInfo")
public class FmOrderInfoController extends BaseController {
	@Autowired
	private FmOrderInfoService fmorderinfoService;

}