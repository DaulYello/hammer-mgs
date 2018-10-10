package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.GcActivity;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.dao.FmRpoolMapper;

import java.util.List;

@Transactional
@Service
public class FmRpoolService {
	@Autowired
	private FmRpoolMapper fmrpoolMapper;

	public FmRpoolMapper getFmRpoolMapper() {
		return fmrpoolMapper;
	}

	public Pagenation<FmRpool> queryIntegrals(PageQuery pageQuery){
		try {
			PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
			List<FmRpool> fmRpools = fmrpoolMapper.selectAll();
			return PageUtil.toPagedResult(fmRpools);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}