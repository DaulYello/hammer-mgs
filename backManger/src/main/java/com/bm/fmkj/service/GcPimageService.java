package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcPimageMapper;

@Transactional
@Service
public class GcPimageService {
	@Autowired
	private GcPimageMapper gcpimageMapper;

	public GcPimageMapper getGcPimageMapper() {
		return gcpimageMapper;
	}

}