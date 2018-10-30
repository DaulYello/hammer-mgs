package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmCntPool;
import com.bm.fmkj.service.FmCntPoolService;

@Controller
@RequestMapping("/fmkj/FmCntPool")
public class FmCntPoolController extends BaseController {
	@Autowired
	private FmCntPoolService fmcntpoolService;

}