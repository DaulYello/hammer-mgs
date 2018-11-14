package com.bm.fmkj.domain;

import com.bm.fmkj.dao.FmOrderInfo;
import com.bm.fmkj.dao.FmProductInfo;

import java.util.List;

public class ProductDto extends FmProductInfo {

    /**
     * 商品的订单明细
     */
    private List<FmOrderInfo> orderInfos;

    public List<FmOrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<FmOrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

    private String nickname;

    private String telephone;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
