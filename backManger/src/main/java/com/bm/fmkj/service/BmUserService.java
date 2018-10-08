package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.BmUser;
import com.bm.fmkj.dao.BmUserMapper;

@Transactional
@Service
public class BmUserService {
	@Autowired
	private BmUserMapper bmuserMapper;

	public BmUserMapper getBmUserMapper() {
		return bmuserMapper;
	}
	
	
	/**
	 * 后台管理用户登录
	 */

	public boolean userLogin(BmUser bu) {
		BmUser user = bmuserMapper.selectOne(bu);
		if(user==null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 后台管理用户注册
	 */
	public boolean userRegister(BmUser bu) {
		BmUser user = bmuserMapper.selectOne(bu);
		if(user!=null) {
			return false;
		}
		int row = bmuserMapper.insertSelective(bu);
		if(row>0) {
			return true;
		}
		return false;
		
	}
}