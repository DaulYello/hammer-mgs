package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.dao.FmRecyleLogMapper;

@Transactional
@Service
public class FmRecyleLogService {
	@Autowired
	private FmRecyleLogMapper fmrecylelogMapper;

	public FmRecyleLogMapper getFmRecyleLogMapper() {
		return fmrecylelogMapper;
	}

}