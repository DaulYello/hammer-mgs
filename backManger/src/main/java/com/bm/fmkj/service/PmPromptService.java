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
import com.bm.fmkj.dao.PmPrompt;
import com.bm.fmkj.dao.PmPromptMapper;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class PmPromptService {
	@Autowired
	private PmPromptMapper pmpromptMapper;

	private Logger LOGGER= LoggerFactory.getLogger(PmPromptService.class);

	public PmPromptMapper getPmPromptMapper() {
		return pmpromptMapper;
	}

    public Pagenation<PmPrompt> getPromptInfo(PageQuery pageQuery) {
		LOGGER.info("PmPromptService通过任务id查询任务的温馨提示，参数："+ JSON.toJSONString(pageQuery));
		List<PmPrompt> pmPrompts = pmpromptMapper.getPromptInfo(pageQuery.getParam());
		return PageUtil.toPagedResult(pmPrompts);
    }

	public int savePromptInfo(PmPrompt prompt) {
		LOGGER.info("PmPromptService保存温馨提示消息，参数："+ JSON.toJSONString(prompt));
		prompt.setCreateDate(new Date());
		if(prompt.getId() == 0){
			LOGGER.info("prompt.getId() == null,新增");
			return pmpromptMapper.savePromptInfo(prompt);
		}else{
			LOGGER.info("prompt.getId() != null,修改");
			return pmpromptMapper.updateByPrimaryKeySelective(prompt);
		}
	}

	public Boolean deletePromptInfo(String id) {
		LOGGER.info("PmPromptService删除提示消息，参数："+ JSON.toJSONString(id));
		PmPrompt prompt = new PmPrompt();
		prompt.setId(Integer.parseInt(id));
		return pmpromptMapper.delete(prompt)>0 ? true : false;
	}
}