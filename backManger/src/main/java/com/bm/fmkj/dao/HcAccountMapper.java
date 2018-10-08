package com.bm.fmkj.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.bm.fmkj.model.PageModel;

import tk.mybatis.mapper.common.Mapper;

public interface HcAccountMapper extends Mapper<HcAccount> {
	
	/**
	 * 分页查询用户邀请人数周排行榜
	 */
	public List<HcAccount> queryUserRank(PageModel<HcAccount> pm);
	
	
	/**
	 * 条件查询好友
	 * @param pm
	 * @return
	 */
	public List<HcAccount> queryUserByExample(HashMap<String, Object> param);
	
	/**
	 * 获取用户的证件照片进行审核验证
	 * @return
	 */
	public List<T> queryUserIdImage(PageModel<HcAccount> pm);
	
	/**
	 * 分页查询用户的信息
	 * @param pageQuery
	 * @return
	 */
	public List<HcAccount> queryUserInfo(HashMap<String, Object> hashMap);
	
	/**
	 * 查询黑名单用户
	 * @param pm
	 * @return
	 */
	public List<HcAccount> queryUserBlack(PageModel<HcAccount> pm);
	
	/**
	 * 通过电话号码或者竟锤id
	 * @param ha
	 * @return
	 */
	public HcAccount selectUserByExample(HcAccount ha);
}