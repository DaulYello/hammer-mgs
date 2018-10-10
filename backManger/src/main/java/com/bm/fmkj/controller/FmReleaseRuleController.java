package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmReleaseRule;
import com.bm.fmkj.service.FmReleaseRuleService;

@Controller
@RequestMapping("/fmkj/FmReleaseRule")
public class FmReleaseRuleController extends BaseController {
	@Autowired
	private FmReleaseRuleService fmreleaseruleService;

}