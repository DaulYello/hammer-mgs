package com.bm.fmkj.service;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.druid.support.logging.Log;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.controller.HcNoticesController;
import com.bm.fmkj.dao.HcNotices;
import com.bm.fmkj.dao.HcNoticesMapper;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class HcNoticesService {
	@Autowired
	private HcNoticesMapper hcnoticesMapper;

	private final static Logger log = LoggerFactory.getLogger(HcNoticesService.class);
	
	public HcNoticesMapper getHcNoticesMapper() {
		return hcnoticesMapper;
	}
	
	public Pagenation<HcNotices> selectNotices(PageQuery pageQuery){
		
		PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
		
		return PageUtil.toPagedResult(hcnoticesMapper.selectNotices(pageQuery.getParam()));
	}
	
	public int savePublishNotice(HashMap<String,Object> param) {
		try {
			HcNotices notices = new HcNotices();
			log.debug("发布消息---》"+(String)param.get("contents"));
			notices.setContents((String)param.get("contents"));
			notices.setTitle((String)param.get("title"));
			notices.setTime(new Date());
			notices.setPass(1);
			return hcnoticesMapper.insert(notices);
		} catch (Exception e) {
			throw new RuntimeException("发布消息失败");
		}
	}
	
	public int deleteTicketById(HashMap<String,Object> param) {
		try {
			log.debug("测试====="+(String)param.get("id"));
			int id = Integer.parseInt((String)param.get("id"));
			return hcnoticesMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new RuntimeException("删除消息失败"+e.getMessage());
		}
	}
	
	public int editNotifySys(HashMap<String,Object> param) {
		try {
			HcNotices notices = new HcNotices();
			log.debug("修改发布消息---》"+(String)param.get("id"));
			log.debug("修改发布消息---》"+(String)param.get("contents"));
			int id = Integer.parseInt((String)param.get("id"));
			notices.setId(id);
			log.debug("noticeID"+notices.getId());
			notices.setContents((String)param.get("contents"));
			notices.setTitle((String)param.get("title"));
			notices.setTime(new Date());
			notices.setPass(1);
			return hcnoticesMapper.updateByPrimaryKey(notices);
		} catch (Exception e) {
			throw new RuntimeException("修改发布消息失败");
		}
	}
}