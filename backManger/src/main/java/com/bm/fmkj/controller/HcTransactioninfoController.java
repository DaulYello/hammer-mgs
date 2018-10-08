package com.bm.fmkj.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bm.fmkj.dao.HcTransactioninfo;
import com.bm.fmkj.model.PageModel;
import com.bm.fmkj.service.HcTransactioninfoService;
import com.xl.utils.BaseController;

@Controller
@RequestMapping("/fmkj/HcTransactioninfo")
public class HcTransactioninfoController extends BaseController {
	@Autowired
	private HcTransactioninfoService hctransactioninfoService;

	/**
	 * 保存用户的交易信息
	 * @param assets
	 * @return
	 */
	@RequestMapping(value="saveTransactionInfo",method = RequestMethod.POST)
	@ResponseBody
	public boolean saveTransactionInfo(HcTransactioninfo transactioninfo) {
		
		return hctransactioninfoService.saveTransactionInfo(transactioninfo);
	}
	
	/**
	 * 通过用户条件，查询用户的交易记录，并带上资产信息
	 * @param ph
	 * @return
	 */
	@RequestMapping(value="selectAssetsAndTransaction",method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, Object> selectAssetsAndTransaction(PageModel<HcTransactioninfo> ph) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer page = ph.getPage();
		if(page == null) {
			ph.setPage(1);
		}
		List<HcTransactioninfo> list = hctransactioninfoService.selectAssetsAndTransaction(ph);
		map.put("transactionsInfo", list);
		return map;
	}
	
	
}