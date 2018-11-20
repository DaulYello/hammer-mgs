package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmStrategy;
import com.bm.fmkj.service.PmStrategyService;

@Controller
@RequestMapping("/fmkj/PmStrategy")
public class PmStrategyController extends BaseController {
	@Autowired
	private PmStrategyService pmstrategyService;

}