package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcBuyticketrecordService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcBuyticketrecord")
public class GcBuyticketrecordController extends BaseController {
	@Autowired
	private GcBuyticketrecordService gcbuyticketrecordService;

}