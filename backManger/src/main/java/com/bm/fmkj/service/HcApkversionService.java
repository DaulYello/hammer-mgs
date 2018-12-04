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
import com.bm.fmkj.dao.HcApkversion;
import com.bm.fmkj.dao.HcApkversionMapper;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class HcApkversionService {
	private Logger LOGGER= LoggerFactory.getLogger(HcApkversionService.class);
	@Autowired
	private HcApkversionMapper hcapkversionMapper;

	public HcApkversionMapper getHcApkversionMapper() {
		return hcapkversionMapper;
	}

    public Pagenation<HcApkversion> getVersionPage(PageQuery pageQuery) {
		LOGGER.info("getVersionPage查询版本列表，参数："+ JSON.toJSONString(pageQuery));
		List<HcApkversion> taskDtos = hcapkversionMapper.getVersionPage(pageQuery.getParam());
		return PageUtil.toPagedResult(taskDtos);
    }

	public int addVersion(HcApkversion apkversion) {
		LOGGER.info("addVersion保存任版本表，参数："+ JSON.toJSONString(apkversion));
		apkversion.setCreateTime(new Date());
		return hcapkversionMapper.insert(apkversion);
	}

	public int updateVersion(HcApkversion apkversion) {
		LOGGER.info("addVersion修改版本列表，参数："+ JSON.toJSONString(apkversion));
		apkversion.setUpdateTime(new Date());
		return hcapkversionMapper.updateByPrimaryKeySelective(apkversion);
	}

	public int deleteVersion(int id) {
		LOGGER.info("deleteVersion删除版本列表，参数："+ JSON.toJSONString(id));
		return hcapkversionMapper.deleteByPrimaryKey(id);
	}
}