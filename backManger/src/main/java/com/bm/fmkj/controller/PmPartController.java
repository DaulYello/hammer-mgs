package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmPart;
import com.bm.fmkj.service.PmPartService;

@Controller
@RequestMapping("/fmkj/PmPart")
public class PmPartController extends BaseController {
	@Autowired
	private PmPartService pmpartService;

}