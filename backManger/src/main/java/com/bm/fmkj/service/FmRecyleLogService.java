package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.FmReleaseRule;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.dao.FmRecyleLogMapper;

import java.util.List;

@Transactional
@Service
public class FmRecyleLogService {
	@Autowired
	private FmRecyleLogMapper fmrecylelogMapper;

	public FmRecyleLogMapper getFmRecyleLogMapper() {
		return fmrecylelogMapper;
	}


	public Pagenation<FmRecyleLog> getFmRecyleLogs(PageQuery pageQuery){
		try {
			PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
			List<FmRecyleLog> fmRecyleLogs = fmrecylelogMapper.getFmRecyleLogs(pageQuery.getParam());
			return PageUtil.toPagedResult(fmRecyleLogs);
		} catch (Exception e) {
			throw new RuntimeException("查询CNT变化日志信息失败！");
		}
	}

}