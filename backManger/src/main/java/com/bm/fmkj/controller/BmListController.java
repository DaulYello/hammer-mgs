package com.bm.fmkj.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.BmList;
import com.bm.fmkj.dao.HcAccount;
import com.bm.fmkj.service.BmListService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/BmList")
public class BmListController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BmListController.class);
	
	@Autowired
	private BmListService bmlistService;
	
	/**
	 * 名单用户列表查询
	 */
	@RequestMapping(value="queryUserByExample",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<HcAccount>> queryUserByExample(@RequestParam HashMap<String, Object> params){
		LOGGER.info("queryUserByExample-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<HcAccount> pagedResult = bmlistService.queryUserByExample(pageQuery);
        return new BaseResult<Pagenation<HcAccount>>(BaseResultEnum.SUCCESS,pagedResult);
	}
	/**
	 * 通过电话号码或者竟锤id
	 * @param ha
	 * @return
	 */
	@RequestMapping(value="selectUserByExample",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> selectUserByExample(HcAccount ha) {
		
		HashMap<String,Object> map = new HashMap<>();
		
		HcAccount account = bmlistService.selectUserByExample(ha);
		if(account == null) {
			System.out.println("account"+account);
			map.put("code", "500");
			map.put("message", "沒有這個用戶！");
		}else {
			System.out.println("account"+account);
			map.put("code", "200");
			map.put("account", account);
		}
		return map;
	}
	/**
	 * 名单新增(含修改)
	 */
	@RequestMapping(value="insertListByUid",method=RequestMethod.POST)
	@ResponseBody
	public int insertListByUid(BmList bl) {
		return bmlistService.insertBmListByExample(bl);
	}
	
	/**
	 * 通过uid移除名单
	 */
	@RequestMapping(value="deleteListByUid",method=RequestMethod.POST)
	@ResponseBody
	public int deleteListById(BmList bl) {
		return bmlistService.deleteBmListByUid(bl);
	}
	
	/**
	 * 拉入黑名单
	 * @param bl
	 */
	@RequestMapping(value="drawBlackList",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> drawBlackList(BmList bl) {
		LOGGER.debug("拉入黑名单用户的id="+bl.getId());
		if(bl == null || bl.getUid() == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK, false);
		}
		boolean result = bmlistService.drawBlackList(bl);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS, result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR, result);
	}
	
	/**
	 * 将用户移除黑名单，这里只的是该用户可以登录APP了
	 * @param bl
	 * @return
	 */
	@RequestMapping(value="removeBlackList",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> removeBlackList(BmList bl) {
		LOGGER.debug("恢复id="+bl.getId());
		if(bl == null || bl.getUid() == null) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK, false);
		}
		boolean result = bmlistService.removeBlackList(bl);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS, result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR, result);
	}
	/**
	 * 将用户设为白名单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="setWhite",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> setWhite(String ids) {
		
		LOGGER.debug("将用户设为白名单id="+ids);
		if(ids == null || "".equals(ids)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK, false);
		}
		boolean result = bmlistService.setWhite(ids);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS, result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR, result);
	}
	
	
	/**
	 * 将用户设为黑名单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="setBlack",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> setBlack(String ids) {
		
		LOGGER.debug("将用户设为黑名单id="+ids);
		if(ids == null || "".equals(ids)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK, false);
		}
		boolean result = bmlistService.setBlack(ids);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS, result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR, result);
	}
	/**
	 * 撤回白名单或者黑名单
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="withdraw",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> withdraw(String uid) {
		
		LOGGER.debug("撤回用户的id="+uid);
		if(uid == null && "".equals(uid)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK, false);
		}
		boolean result = bmlistService.withdraw(uid);
		if(result) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS, result);
		}
		return new BaseResult<Boolean>(BaseResultEnum.ERROR, result);
	}
}