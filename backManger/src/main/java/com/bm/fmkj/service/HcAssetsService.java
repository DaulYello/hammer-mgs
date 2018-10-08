package com.bm.fmkj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.HcAssets;
import com.bm.fmkj.dao.HcAssetsMapper;

@Transactional
@Service
public class HcAssetsService {
	@Autowired
	private HcAssetsMapper hcassetsMapper;

	public HcAssetsMapper getHcAssetsMapper() {
		return hcassetsMapper;
	}
	
	public boolean saveAssetsContent(HcAssets assets) {
		int rowEffect = hcassetsMapper.insertSelective(assets);
		return rowEffect > 0 ?true:false;
	}
	
	public List<HcAssets> selectAssetsContent(HcAssets ph){
		return hcassetsMapper.select(ph);
	}

}