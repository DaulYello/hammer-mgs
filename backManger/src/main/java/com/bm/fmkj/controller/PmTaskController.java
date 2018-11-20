package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmTask;
import com.bm.fmkj.service.PmTaskService;

@Controller
@RequestMapping("/fmkj/PmTask")
public class PmTaskController extends BaseController {
	@Autowired
	private PmTaskService pmtaskService;

}