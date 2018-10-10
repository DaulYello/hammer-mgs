package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmReleaseRule;
import com.bm.fmkj.dao.FmReleaseRuleMapper;

@Transactional
@Service
public class FmReleaseRuleService {
	@Autowired
	private FmReleaseRuleMapper fmreleaseruleMapper;

	public FmReleaseRuleMapper getFmReleaseRuleMapper() {
		return fmreleaseruleMapper;
	}

}