package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.domain.PmStrategyDto;
import com.bm.fmkj.utils.Convert;
import com.bm.fmkj.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class PmStrategyService {
	@Autowired
	private PmStrategyMapper pmstrategyMapper;

	@Autowired
	private PmTaskMapper pmTaskMapper;

	@Autowired
	private PmImageMapper pmImageMapper;

	public PmStrategyMapper getPmStrategyMapper() {
		return pmstrategyMapper;
	}

    public Pagenation<PmStrategyDto> getStrategyPage(PageQuery pageQuery) {
		PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
		return PageUtil.toPagedResult(pmstrategyMapper.selectStrategyList(pageQuery.getParam()));
    }

	public int deleteStrategy(String ids) {

		return pmstrategyMapper.deleteStrategy(Convert.toStrArray(ids));

	}

	public List<PmTask> getTaskList(HashMap<String, Object> params) {

		return pmTaskMapper.queryTaskList();
	}

	public int addStrategy(PmStrategy strategy) {
		strategy.setCreateDate(new Date());
		int row = pmstrategyMapper.addStrategy(strategy);
		if(row > 0){
			String imageStr = strategy.getImageIdStr();
			if(StringUtils.isNotEmpty(imageStr)){
				row = detaIlimageType(strategy.getId(),imageStr);
			}
		}
		return row;
	}

	public int editStrategy(PmStrategy strategy) {
		strategy.setUpdateDate(new Date());
		int row = pmstrategyMapper.updateStrategy(strategy);
		if(row > 0){
			String imageStr = strategy.getImageIdStr();
			if(StringUtils.isNotEmpty(imageStr)){
				//删除旧图片
				pmImageMapper.dropOldImage(strategy.getId());
				row = detaIlimageType(strategy.getId(),imageStr);
			}
		}
		return row;
	}

	public int detaIlimageType(int id,String imageStr){
		HashMap<String, Object> hashMap = new HashMap<>();
		List<Integer> iamgeList = new ArrayList<>();
		String [] imageArray = imageStr.split(",");
		for(int i = 0; i < imageArray.length; i++){
			String imageId = imageArray[i];
			iamgeList.add(Integer.parseInt(imageId));
		}
		hashMap.put("strategyId", id);
		hashMap.put("imageIds", iamgeList);
		int res = pmImageMapper.updateStrategyImage(hashMap);
		if(res > 0){
			return res;
		}else{
			throw new RuntimeException("新增攻略失败");
		}
	}
}