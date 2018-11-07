package com.bm.fmkj.service;

import java.util.Date;
import java.util.HashMap;

import javax.management.RuntimeErrorException;
import javax.persistence.criteria.CriteriaBuilder.In;

import com.bm.fmkj.constant.PointEnum;
import com.bm.fmkj.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.logging.Log;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class HcUserImageService {
	@Autowired
	private HcUserImageMapper hcUserImageMapper;
	@Autowired
	private HcAccountMapper hcAccountMapper;

	@Autowired
    private HcPointsRecordMapper pointsRecordMapper;

	public HcUserImageMapper getHcUserImageMapper() {
		return hcUserImageMapper;
	}

	public Pagenation<HcUserImage> getUserRealInfo(PageQuery pageQuery){
		PageHelper.startPage(pageQuery.getPageNo(),pageQuery.getPageSize());
		return PageUtil.toPagedResult(hcUserImageMapper.getUserRealInfo(pageQuery.getParam()));
	}
	
	public HashMap<String,Object> approveUserRealInfo(HashMap<String,Object> param) {
		try {
		    HashMap<String,Object> map = new HashMap<>();
			int id = Integer.parseInt((String)param.get("id"));
            int flag = Integer.parseInt((String)param.get("flag"));
            HcUserImage hcUserImage= hcUserImageMapper.selectByPrimaryKey(id);
			if(flag==1){
                HcPointsRecord pointsRecord = new HcPointsRecord();
                pointsRecord.setUid(hcUserImage.getUid());
                pointsRecord.setTime(new Date());
                pointsRecord.setPointsId(PointEnum.REAL_AUTHENT.pointId);
                pointsRecord.setPointsNum(PointEnum.REAL_AUTHENT.pointNum);
                int prow = pointsRecordMapper.insert(pointsRecord);
                if(prow<=0){
                    map.put("status",false);
                    map.put("message","用户实认证，添加积分失败！");
                    return map;
                }
				HcAccount hc = hcAccountMapper.selectByPrimaryKey(hcUserImage.getUid());
				hc.setCardStatus(2);
				if(hcAccountMapper.updateByPrimaryKeySelective(hc) <=0){
					map.put("status",false);
					map.put("message","用户实认证，更新认证状态失败！");
					return map;
				}
                map.put("status",true);
                map.put("message","用户实认证审核通过！");
                return map;
			}else{
				HcAccount hc = hcAccountMapper.selectByPrimaryKey(hcUserImage.getUid());
				hc.setCardStatus(-1);
				hc.setRealnamInfo(param.get("rejectReason").toString());
				if(hcAccountMapper.updateByPrimaryKeySelective(hc) <=0){
					map.put("status",false);
					map.put("message","驳回时更新失败！");
					return map;
				}
                map.put("status",true);
                map.put("message","用户实认证驳回成功！");
                return map;
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
	 *//*
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
	}*/

}