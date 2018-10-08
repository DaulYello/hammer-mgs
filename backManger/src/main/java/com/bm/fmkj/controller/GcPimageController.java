package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcPimageService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcPimage")
public class GcPimageController extends BaseController {
	@Autowired
	private GcPimageService gcpimageService;

}