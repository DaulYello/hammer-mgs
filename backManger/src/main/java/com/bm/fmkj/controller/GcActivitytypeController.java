package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcActivitytypeService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcActivitytype")
public class GcActivitytypeController extends BaseController {
	@Autowired
	private GcActivitytypeService gcactivitytypeService;

}