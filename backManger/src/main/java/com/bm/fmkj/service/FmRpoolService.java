package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.GcActivity;
import com.bm.fmkj.domain.Job;
import com.bm.fmkj.job.ScheduleConstants;
import com.bm.fmkj.job.ScheduleUtils;
import com.bm.fmkj.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.dao.FmRpoolMapper;

import java.util.Date;
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

	/**
	 * 新增
	 * @param fmRpool
	 */
	public int insertFmRpool(FmRpool fmRpool) {
		try{
			fmRpool.setCreatedate(new Date());
			return fmrpoolMapper.insert(fmRpool);
		}catch (Exception e){
			e.getMessage();
			return 0;
		}
	}
	/**
	 * 修改
	 * @param fmRpool
	 */
	public int editIntegral(FmRpool fmRpool) {
		try{
			fmRpool.setUpdatedate(new Date());
			if(StringUtils.isNull(fmRpool.getRecycleNum())){
				fmRpool.setRecycleNum(0.0);
			}
			return fmrpoolMapper.updateByPrimaryKeySelective(fmRpool);
		}catch (Exception e){
			e.getMessage();
			return 0;
		}
	}
	/**
	 * 删除
	 * @param fmRpool
	 */
	public void dropIntegration(String id) {
		try{
			FmRpool fmRpool = new FmRpool();
			fmRpool.setId(Integer.parseInt(id));
			fmrpoolMapper.delete(fmRpool);
		}catch (Exception e){
			e.getMessage();
		}
	}
}