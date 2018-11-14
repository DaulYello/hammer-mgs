package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.domain.ProductDto;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class FmProductInfoService {
    @Autowired
    private FmProductInfoMapper fmproductinfoMapper;
    @Autowired
    private FmOrderInfoMapper fmOrderInfoMapper;

    public FmProductInfoMapper getFmProductInfoMapper() {
        return fmproductinfoMapper;
    }


    public Pagenation<ProductDto> queryGoodsList(PageQuery pageQuery) {
        try {
                    PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
            List<ProductDto> productDtos = fmproductinfoMapper.queryGoodsList(pageQuery.getParam());
            for (ProductDto productDto:productDtos) {
                List<FmOrderInfo> orders = fmOrderInfoMapper.queryOdersByPId(productDto.getId());
                productDto.setOrderInfos(orders);
            }
            return PageUtil.toPagedResult(productDtos);
        } catch (Exception e) {
            throw new RuntimeException("查询商品信息失败！");
        }
    }
}