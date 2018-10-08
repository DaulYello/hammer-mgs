package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcCollectService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcCollect")
public class GcCollectController extends BaseController {
	@Autowired
	private GcCollectService gccollectService;

}