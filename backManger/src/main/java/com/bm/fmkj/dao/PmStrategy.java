package com.bm.fmkj.dao;

import javax.persistence.*;
import java.util.Date;

@Table(name = "pm_strategy")
public class PmStrategy {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 任务ID
     */
    private Integer tid;

    /**
     * 攻略
     */
    private String strategy;

    /**
     * 顺序
     */
    private Byte order;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取任务ID
     *
     * @return tid - 任务ID
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置任务ID
     *
     * @param tid 任务ID
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 获取攻略
     *
     * @return strategy - 攻略
     */
    public String getStrategy() {
        return strategy;
    }

    /**
     * 设置攻略
     *
     * @param strategy 攻略
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取顺序
     *
     * @return order - 顺序
     */
    public Byte getOrder() {
        return order;
    }

    /**
     * 设置顺序
     *
     * @param order 顺序
     */
    public void setOrder(Byte order) {
        this.order = order;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}