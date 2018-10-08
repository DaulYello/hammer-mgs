package com.bm.fmkj.base;

import java.util.List;

import com.github.pagehelper.Page;
 
public class PageUtil {
 
    public static <T> Pagenation<T> toPagedResult(List<T> datas) {
        Pagenation<T> result = new Pagenation<T>();
        if (datas instanceof Page) {
            Page page = (Page) datas;
            result.setPageNo(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setDataList(page.getResult());
            result.setTotal(page.getTotal());
            result.setPages(page.getPages());
        }
        else {
            result.setPageNo(1);
            result.setPageSize(datas.size());
            result.setDataList(datas);
            result.setTotal(datas.size());
        }
 
        return result;
    }
}

