package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmPrompt;
import com.bm.fmkj.dao.PmPromptMapper;

@Transactional
@Service
public class PmPromptService {
	@Autowired
	private PmPromptMapper pmpromptMapper;

	public PmPromptMapper getPmPromptMapper() {
		return pmpromptMapper;
	}

}