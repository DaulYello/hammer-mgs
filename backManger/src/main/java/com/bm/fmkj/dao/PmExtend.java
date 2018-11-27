package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pm_extend")
public class PmExtend {
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
     * 列名
     */
    @Column(name = "clounm_key")
    private String clounmKey;

    /**
     * 列名名称
     */
    @Column(name = "clounm_name")
    private String clounmName;

    /**
     * 提示信息
     */
    @Column(name = "clounm_tip")
    private String clounmTip;

    /**
     * 是否为空0、可为空, 1、必填
     */
    @Column(name = "is_empty")
    private Byte isEmpty;

    /**
     * 注释
     */
    @Column(name = "empty_hint")
    private String emptyHint;

    /**
     * 显示顺序
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

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
     * 获取列名
     *
     * @return clounm_key - 列名
     */
    public String getClounmKey() {
        return clounmKey;
    }

    /**
     * 设置列名
     *
     * @param clounmKey 列名
     */
    public void setClounmKey(String clounmKey) {
        this.clounmKey = clounmKey;
    }

    /**
     * 获取列名名称
     *
     * @return clounm_name - 列名名称
     */
    public String getClounmName() {
        return clounmName;
    }

    /**
     * 设置列名名称
     *
     * @param clounmName 列名名称
     */
    public void setClounmName(String clounmName) {
        this.clounmName = clounmName;
    }

    /**
     * 获取提示信息
     *
     * @return clounm_tip - 提示信息
     */
    public String getClounmTip() {
        return clounmTip;
    }

    /**
     * 设置提示信息
     *
     * @param clounmTip 提示信息
     */
    public void setClounmTip(String clounmTip) {
        this.clounmTip = clounmTip;
    }

    /**
     * 获取是否为空0、可为空, 1、必填
     *
     * @return is_empty - 是否为空0、可为空, 1、必填
     */
    public Byte getIsEmpty() {
        return isEmpty;
    }

    /**
     * 设置是否为空0、可为空, 1、必填
     *
     * @param isEmpty 是否为空0、可为空, 1、必填
     */
    public void setIsEmpty(Byte isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * 获取为空提示
     *
     * @return empty_hint - 为空提示
     */
    public String getEmptyHint() {
        return emptyHint;
    }

    /**
     * 设置为空提示
     *
     * @param emptyHint 为空提示
     */
    public void setEmptyHint(String emptyHint) {
        this.emptyHint = emptyHint;
    }

    /**
     * 获取显示顺序
     *
     * @return order_num - 显示顺序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置显示顺序
     *
     * @param orderNum 显示顺序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}