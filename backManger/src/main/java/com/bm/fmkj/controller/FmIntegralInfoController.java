package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmIntegralInfo;
import com.bm.fmkj.service.FmIntegralInfoService;

@Controller
@RequestMapping("/fmkj/FmIntegralInfo")
public class FmIntegralInfoController extends BaseController {
	@Autowired
	private FmIntegralInfoService fmintegralinfoService;

}