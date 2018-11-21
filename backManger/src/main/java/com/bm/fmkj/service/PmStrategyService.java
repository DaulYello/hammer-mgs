package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.PmTask;
import com.bm.fmkj.dao.PmTaskMapper;
import com.bm.fmkj.domain.PmStrategyDto;
import com.bm.fmkj.utils.Convert;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmStrategy;
import com.bm.fmkj.dao.PmStrategyMapper;

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
		return pmstrategyMapper.addStrategy(strategy);
	}
}