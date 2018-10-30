package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmAssetsPoundage;
import com.bm.fmkj.service.FmAssetsPoundageService;

@Controller
@RequestMapping("/fmkj/FmAssetsPoundage")
public class FmAssetsPoundageController extends BaseController {
	@Autowired
	private FmAssetsPoundageService fmassetspoundageService;

}