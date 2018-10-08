package com.bm.fmkj.dao;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hc_transactioninfo")
public class HcTransactioninfo {
    @Id
    private Integer id;

    /**
     * hc_assets中的用户userId
     */
    private Integer uid;

    /**
     * 用户资产种类
     */
    private String askind;

    /**
     * 用户交易信息
     */
    private String transactioninfo;

    /**
     * 添加交易记录的时间
     */
    private Date addtime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取hc_assets中的用户userId
     *
     * @return uid - hc_assets中的用户userId
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置hc_assets中的用户userId
     *
     * @param uid hc_assets中的用户userId
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取用户资产种类
     *
     * @return askind - 用户资产种类
     */
    public String getAskind() {
        return askind;
    }

    /**
     * 设置用户资产种类
     *
     * @param askind 用户资产种类
     */
    public void setAskind(String askind) {
        this.askind = askind;
    }

    /**
     * 获取用户交易信息
     *
     * @return transactioninfo - 用户交易信息
     */
    public String getTransactioninfo() {
        return transactioninfo;
    }

    /**
     * 设置用户交易信息
     *
     * @param transactioninfo 用户交易信息
     */
    public void setTransactioninfo(String transactioninfo) {
        this.transactioninfo = transactioninfo;
    }

    /**
     * 获取添加交易记录的时间
     *
     * @return addtime - 添加交易记录的时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加交易记录的时间
     *
     * @param addtime 添加交易记录的时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}