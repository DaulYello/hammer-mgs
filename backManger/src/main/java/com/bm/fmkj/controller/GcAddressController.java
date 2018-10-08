package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bm.fmkj.service.GcAddressService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/GcAddress")
public class GcAddressController extends BaseController {
	@Autowired
	private GcAddressService gcaddressService;

}