package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.dao.GcAddressMapper;

@Transactional
@Service
public class GcAddressService {
	@Autowired
	private GcAddressMapper gcaddressMapper;

	public GcAddressMapper getGcAddressMapper() {
		return gcaddressMapper;
	}

}