package com.bm.fmkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bm.fmkj.dao.PmImage;
import com.bm.fmkj.dao.PmImageMapper;

@Transactional
@Service
public class PmImageService {
	@Autowired
	private PmImageMapper pmimageMapper;

	public PmImageMapper getPmImageMapper() {
		return pmimageMapper;
	}

    public int insert(PmImage image) {
		return pmimageMapper.insertImage(image);
    }
}