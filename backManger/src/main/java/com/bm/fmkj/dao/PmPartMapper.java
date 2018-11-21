package com.bm.fmkj.dao;

import com.bm.fmkj.domain.PmPartDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface PmPartMapper extends Mapper<PmPart> {

    List<PmPartDto> selectPartList(HashMap<String, Object> param);

    void auditPart(PmPart pmPart);
}