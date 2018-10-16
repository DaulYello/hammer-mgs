package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.service.FmRecyleLogService;

@Controller
@RequestMapping("/fmkj/FmRecyleLog")
public class FmRecyleLogController extends BaseController {
	@Autowired
	private FmRecyleLogService fmrecylelogService;

}