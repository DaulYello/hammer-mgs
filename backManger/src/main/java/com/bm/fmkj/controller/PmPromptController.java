package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmPrompt;
import com.bm.fmkj.service.PmPromptService;

@Controller
@RequestMapping("/fmkj/PmPrompt")
public class PmPromptController extends BaseController {
	@Autowired
	private PmPromptService pmpromptService;

}