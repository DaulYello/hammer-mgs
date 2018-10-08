package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcJoinactivityrecordService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcJoinactivityrecord")
public class GcJoinactivityrecordController extends BaseController {
	@Autowired
	private GcJoinactivityrecordService gcjoinactivityrecordService;

}