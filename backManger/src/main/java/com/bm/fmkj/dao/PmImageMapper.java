package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmImageMapper extends Mapper<PmImage> {
    List<PmImage> queryTaskImagesByID(Integer tid);
}