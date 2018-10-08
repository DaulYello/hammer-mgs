package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcNewcollectService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcNewcollect")
public class GcNewcollectController extends BaseController {
	@Autowired
	private GcNewcollectService gcnewcollectService;

}