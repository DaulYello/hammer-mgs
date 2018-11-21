package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.domain.PmPartDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmPart;
import com.bm.fmkj.dao.PmPartMapper;

import java.util.Date;

@Transactional
@Service
public class PmPartService {
	@Autowired
	private PmPartMapper pmpartMapper;

	public PmPartMapper getPmPartMapper() {
		return pmpartMapper;
	}

    public Pagenation<PmPartDto> getPartPage(PageQuery pageQuery) {
		PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
		return PageUtil.toPagedResult(pmpartMapper.selectPartList(pageQuery.getParam()));
    }

	public int auditPart(PmPart pmPart) {
		pmPart.setUpdateDate(new Date());
		return pmpartMapper.updateByPrimaryKeySelective(pmPart);
	}
}