package com.bm.fmkj.service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.HcAccount;
import com.bm.fmkj.dao.HcAccountMapper;
import com.bm.fmkj.model.PageModel;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class HcAccountService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HcAccountService.class);

	@Autowired
	private HcAccountMapper hcaccountMapper;

	public HcAccountMapper getHcAccountMapper() {
		return hcaccountMapper;
	}
	
	/**
	 * 分页查询用户邀请人数排行榜
	 */
	public List<HcAccount> queryUserRank(PageModel<HcAccount> pm){
		return hcaccountMapper.queryUserRank(pm);
	}
	
	/**
	 * 通过id查询用户
	 */
	public HcAccount queryUserById(HcAccount ha) {
		return hcaccountMapper.selectOne(ha);
	}
	
	/**
	 *获取用户的证件照片进行审核验证
	 * @return
	 */
	public List<T> queryUserIdImage(PageModel<HcAccount> pm){
		return hcaccountMapper.queryUserIdImage(pm);
	}
	
	/**
	 * 分页获取用户的信息
	 * @param pm
	 * @return
	 */
	public Pagenation<HcAccount> queryUserInfo(PageQuery pageQuery) {
		try {
			PageHelper.startPage(pageQuery.getPageNo(),pageQuery.getPageSize());  
			List<HcAccount> hccounts = hcaccountMapper.queryUserInfo(pageQuery.getParam());
			return PageUtil.toPagedResult(hccounts);
		} catch (Exception e) {
			LOGGER.error("用户信息查询异常：" + e.getMessage());
			throw new RuntimeException();
		}
	}
	/**
	 * 获取黑名单用户
	 * @param pm
	 * @return
	 */
	public List<HcAccount> queryUserBlack(PageModel<HcAccount> pm){
		return hcaccountMapper.queryUserBlack(pm);
	}

}