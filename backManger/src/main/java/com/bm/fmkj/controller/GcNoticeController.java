package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcNoticeService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcNotice")
public class GcNoticeController extends BaseController {
	@Autowired
	private GcNoticeService gcnoticeService;

}