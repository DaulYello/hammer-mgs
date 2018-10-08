package com.bm.fmkj.service;

import java.util.HashMap;

import javax.management.RuntimeErrorException;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.logging.Log;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.HcAccount;
import com.bm.fmkj.dao.HcAccountMapper;
import com.bm.fmkj.dao.HcUserImage;
import com.bm.fmkj.dao.HcUserImageMapper;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class HcUserImageService {
	@Autowired
	private HcUserImageMapper hcUserImageMapper;
	@Autowired
	private HcAccountMapper hcAccountMapper;

	public HcUserImageMapper getHcUserImageMapper() {
		return hcUserImageMapper;
	}

	public Pagenation<HcUserImage> getUserRealInfo(PageQuery pageQuery){
		PageHelper.startPage(pageQuery.getPageNo(),pageQuery.getPageSize());
		return PageUtil.toPagedResult(hcUserImageMapper.getUserRealInfo(pageQuery.getParam()));
	}
	
	public boolean approveUserRealInfo(HashMap<String,Object> param) {
		try {
			int id = Integer.parseInt((String)param.get("id"));
			int status = Integer.parseInt((String)param.get("status"));
			HcUserImage userImage = new HcUserImage();
			if(status==1){
				System.out.println("实名认证的ID："+id);
				userImage.setId(id);
				userImage.setStatus(1);
				return hcUserImageMapper.updateByPrimaryKeySelective(userImage)>0?true:false;
			}else{
				System.out.println("status："+status);
				userImage.setId(id);
				userImage.setStatus(1);
				return hcUserImageMapper.updateByPrimaryKeySelective(userImage)>0?true:false;
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 通过主键ID查询对象
	 * @param hi
	 * @return
	 */
	public HcUserImage queryUserImageById(HcUserImage hi) {
//		Example example = new Example(HcUserImage.class);
//		Criteria criteria = example.createCriteria();
//		criteria.andCondition("uid=", hi.getUid());
		return hcUserImageMapper.selectOne(hi);
	}
	
	/**
	 * 更新证件的审核状态
	 * @param hi
	 * @return
	 */
	public boolean updateById(HcUserImage hi) {
		int irow = hcUserImageMapper.updateByPrimaryKeySelective(hi);
		HcAccount account = new HcAccount();
		account.setId(hi.getUid());
		if(hi.getStatus() == 1) {//审核通过
			account.setCardStatus(2);
		}else if(hi.getStatus() == 2) {//审核未通过
			account.setCardStatus(3);
		}
		int arow = hcAccountMapper.updateByPrimaryKeySelective(account);
		if(irow == 0 || arow == 0) {
			return false;
		}else {
			return true; 
		}
	}

}