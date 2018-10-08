package com.bm.fmkj.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.dao.BmUser;
import com.bm.fmkj.service.BmUserService;
import com.bm.fmkj.utils.TokenUtil;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/BmUser")
public class BmUserController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(HcAccountController.class);
	@Autowired
	private BmUserService bmuserService;
	
	/**
	 * 用户登录
	 * @param bu
	 * @return
	 */
	@RequestMapping(value="login",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<HashMap<String, Object>> userLogin(BmUser bu) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		boolean isSuccess = bmuserService.userLogin(bu);
		if(!isSuccess) {
			return new BaseResult<HashMap<String, Object>>(BaseResultEnum.NOACCESS.status, "用户名或密码错误", result);
		}
		result.put("user", bu);
		result.put("token", TokenUtil.genToken());
        return new BaseResult<HashMap<String, Object>>(BaseResultEnum.SUCCESS.status, "登录成功", result);
	}
	
	/**
	 * 用户注册
	 */
	@RequestMapping(value="register",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> userRegister(BmUser bu) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		boolean result = bmuserService.userRegister(bu);
		if(!result) {
			map.put("message", "注册失败!");
			map.put("code", "500");
			return map;
		}
		map.put("message", "注册成功!");
		map.put("code", "200");
		return map;
	}
}