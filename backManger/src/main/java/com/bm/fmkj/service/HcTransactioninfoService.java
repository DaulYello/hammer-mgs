package com.bm.fmkj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.HcTransactioninfo;
import com.bm.fmkj.dao.HcTransactioninfoMapper;
import com.bm.fmkj.model.PageModel;

@Transactional
@Service
public class HcTransactioninfoService {
	@Autowired
	private HcTransactioninfoMapper hctransactioninfoMapper;

	public HcTransactioninfoMapper getHcTransactioninfoMapper() {
		return hctransactioninfoMapper;
	}
	
	public boolean saveTransactionInfo(HcTransactioninfo transactioninfo) {
		int rowEffect = hctransactioninfoMapper.insertSelective(transactioninfo);
		return rowEffect > 0 ? true:false;
	}
	
	public List<HcTransactioninfo> selectAssetsAndTransaction(PageModel<HcTransactioninfo> ph){
		return hctransactioninfoMapper.selectAssetsAndTransaction(ph);
	}
}