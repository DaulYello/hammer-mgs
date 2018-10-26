package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmCntInfo;
import com.bm.fmkj.service.FmCntInfoService;

@Controller
@RequestMapping("/fmkj/FmCntInfo")
public class FmCntInfoController extends BaseController {
	@Autowired
	private FmCntInfoService fmcntinfoService;

}