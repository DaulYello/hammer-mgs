package com.bm.fmkj.dao;

import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface HcApkversionMapper extends Mapper<HcApkversion> {
    List<HcApkversion> getVersionPage(HashMap<String, Object> param);
}