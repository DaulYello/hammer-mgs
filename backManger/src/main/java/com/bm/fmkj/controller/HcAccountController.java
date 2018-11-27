package com.bm.fmkj.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.xl.utils.DateUtil;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.HcAccount;
import com.bm.fmkj.model.PageModel;
import com.bm.fmkj.service.HcAccountService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/HcAccount")
public class HcAccountController extends BaseController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HcAccountController.class);

	@Autowired
	private HcAccountService hcaccountService;
	
	
	/**
	 * 用户邀请人数周排行榜页面跳转
	 */
	@RequestMapping(value="queryUserRank",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView queryUserRank(PageModel<HcAccount> pm){
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer page = pm.getPage();
		if(page==null) {
			pm.setPage(1);
		}
		List<HcAccount> list = hcaccountService.queryUserRank(pm);
		map.put("rankList", list);
		map.put("page", pm.getPage());
		return new ModelAndView("dynamic/rank", map);
	}
	
	/**
	 * 通过id查询用户
	 */
	@RequestMapping(value="queryUserById",method=RequestMethod.POST)
	@ResponseBody
	public HcAccount queryUserById(HcAccount ha) {
		return hcaccountService.queryUserById(ha);
	}
	
	/**
	 * 获取用户的证件照片进行审核验证
	 * @return
	 */
	@RequestMapping(value="queryUserIdImage")
	public ModelAndView queryUserIdImage(PageModel<HcAccount> pm,HcAccount ha) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		pm.setEntity(ha);
		Integer page = pm.getPage();
		if(page == null) {
			pm.setPage(1);
		}
		List<T> cardImages=hcaccountService.queryUserIdImage(pm);
		map.put("page",pm.getPage());
		map.put("cardImages", cardImages);
		return new ModelAndView("dynamic/idCardVerify",map);
	}
	
	/**
	 * 查询用户的详细信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="queryUserInfo", method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<HcAccount>> queryUserInfo(@RequestParam HashMap<String, Object> params) {
		LOGGER.info("queryUserInfo-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		if(null==params.get("sort")){
			params.put("sort","desc");
		}
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<HcAccount> pagedResult = hcaccountService.queryUserInfo(pageQuery);
        return new BaseResult<Pagenation<HcAccount>>(BaseResultEnum.SUCCESS,pagedResult);
	}
	
	
	
	/**
	 * 查询所有的黑名单
	 * @param params
	 * @return
	 */
	@RequestMapping(value="queryUserBlack",method=RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<HcAccount>> queryUserBlack(@RequestParam HashMap<String, Object> params) {
		LOGGER.info("queryUserInfo-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		Pagenation<HcAccount> pagedResult = new Pagenation<>();
		//业务实现
		//List<HcAccount> list = hcaccountService.queryUserBlack(pageQuery);
        return new BaseResult(BaseResultEnum.SUCCESS,pagedResult);
	}
}