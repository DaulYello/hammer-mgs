package com.bm.fmkj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.GcTicket;
import com.bm.fmkj.dao.GcTicketMapper;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class GcTicketService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(GcTicketService.class);

	@Autowired
	private GcTicketMapper gcticketMapper;

	public GcTicketMapper getGcTicketMapper() {
		return gcticketMapper;
	}
	
	/**
	 * 传入票种id查询票种信息
	 */
	public GcTicket selectTicketById(GcTicket gt) {
		return gcticketMapper.selectOne(gt);
	}
	
	/**
	 *分页查询所有票种 
	 * @param params 
	 */
	public Pagenation<GcTicket> selectAllTicket(PageQuery pageQuery) {
		try {
			//startPage是告诉拦截器开始分页，分页参数是这两个。
			PageHelper.startPage(pageQuery.getPageNo(),pageQuery.getPageSize());
			return PageUtil.toPagedResult(gcticketMapper.selectTicketInfo(pageQuery.getParam()));
		} catch (Exception e) {
			LOGGER.error("分页查询所有票种异常：" + e.getMessage());
			throw new RuntimeException();
		}
	}
	
	/**
	 *查询所有票种 
	 * @param params 
	 */
	public List<GcTicket> selectAllTicket(){
		return gcticketMapper.selectAll();
	}
	
	/**
	 * 新建票种
	 */
	public boolean insertTicketByExample(GcTicket gt) {
		int row = gcticketMapper.insertSelective(gt);
		if(row>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 传入实体id删除票种
	 */
	public boolean deleteTicketById(GcTicket gt) {
		int row = gcticketMapper.delete(gt);
		if(row==0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 传入实体id根据实体属性更新票种
	 */
	public boolean updateTicketById(GcTicket gt) {
		int row = gcticketMapper.updateByPrimaryKeySelective(gt);
		if(row==0) {
			return false;
		}
		return true;
	}

}