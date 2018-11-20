package com.bm.fmkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmImage;
import com.bm.fmkj.service.PmImageService;

@Controller
@RequestMapping("/fmkj/PmImage")
public class PmImageController extends BaseController {
	@Autowired
	private PmImageService pmimageService;

}