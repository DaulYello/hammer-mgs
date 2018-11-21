package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

public interface PmImageMapper extends Mapper<PmImage> {

    int insertImage(PmImage image);
}