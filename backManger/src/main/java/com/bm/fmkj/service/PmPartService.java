package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmPart;
import com.bm.fmkj.dao.PmPartMapper;

@Transactional
@Service
public class PmPartService {
	@Autowired
	private PmPartMapper pmpartMapper;

	public PmPartMapper getPmPartMapper() {
		return pmpartMapper;
	}

}