package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.FmRpool;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmReleaseRule;
import com.bm.fmkj.dao.FmReleaseRuleMapper;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class FmReleaseRuleService {
	@Autowired
	private FmReleaseRuleMapper fmreleaseruleMapper;

	public FmReleaseRuleMapper getFmReleaseRuleMapper() {
		return fmreleaseruleMapper;
	}


	/**
	 * 积分规则分页查询
	 * @param pageQuery
	 * @return
	 */
	public Pagenation<FmReleaseRule> queryIntegralRule(PageQuery pageQuery){
		try {
			PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
			List<FmReleaseRule> fmReleaseRules = fmreleaseruleMapper.selectIntegralRules();
			return PageUtil.toPagedResult(fmReleaseRules);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 新增
	 * @param fmRpool
	 */
	public int addIntegrateRule(FmReleaseRule fmReleaseRule) {
		try{
			fmReleaseRule.setCreateDate(new Date());
			return fmreleaseruleMapper.insert(fmReleaseRule);
		}catch (Exception e){
			e.getMessage();
			return 0;
		}
	}
	/**
	 * 修改
	 * @param fmRpool
	 */
	public int editIntegralRule(FmReleaseRule fmReleaseRule) {
		try{
			fmReleaseRule.setUpdateDate(new Date());
			return fmreleaseruleMapper.updateByPrimaryKeySelective(fmReleaseRule);
		}catch (Exception e){
			e.getMessage();
			return 0;
		}
	}
	/**
	 * 删除
	 * @param id
	 */
	public void dropIntegrationRule(String id) {
		try{
			FmReleaseRule fmReleaseRule = new FmReleaseRule();
			fmReleaseRule.setId(Integer.parseInt(id));
			fmreleaseruleMapper.delete(fmReleaseRule);
		}catch (Exception e){
			e.getMessage();
		}
	}
}