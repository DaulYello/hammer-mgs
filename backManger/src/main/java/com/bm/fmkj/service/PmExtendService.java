package com.bm.fmkj.service;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmExtend;
import com.bm.fmkj.dao.PmExtendMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class PmExtendService {
	@Autowired
	private PmExtendMapper pmextendMapper;

	private Logger LOGGER= LoggerFactory.getLogger(PmExtendService.class);

	public PmExtendMapper getPmExtendMapper() {
		return pmextendMapper;
	}

    public Pagenation<PmExtend> getExtendInfo(PageQuery pageQuery) {
		LOGGER.info("PmExtendService通过任务id查询任务的扩展字段，参数："+ JSON.toJSONString(pageQuery));
		List<PmExtend> pmPrompts = pmextendMapper.getExtendInfo(pageQuery.getParam());
		return PageUtil.toPagedResult(pmPrompts);
    }

	public int saveExtendInfo(PmExtend extend) {
		LOGGER.info("PmExtendService保存任务的扩展字段，参数："+ JSON.toJSONString(extend));
		if(extend.getId() == 0){
			extend.setCreateDate(new Date());
			LOGGER.info("extend.getId() == null,新增");
			return pmextendMapper.saveExtendInfo(extend);
		}else{
			LOGGER.info("extend.getId() != null,修改");
			extend.setUpdateDate(new Date());
			return pmextendMapper.updateByPrimaryKeySelective(extend);
		}

	}

	public Boolean deleteExtendInfo(String id) {
		LOGGER.info("PmExtendService删除扩展消息，参数："+ JSON.toJSONString(id));
		PmExtend extend = new PmExtend();
		extend.setId(Integer.parseInt(id));
		return pmextendMapper.delete(extend)>0 ? true : false;
	}
}