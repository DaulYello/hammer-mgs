package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_release_rule")
public class FmReleaseRule {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * R积分池
     */
    @Column(name = "rpool_id")
    private Integer rpoolId;

    /**
     * 飞羽区间最小值
     */
    @Column(name = "fy_min")
    private Double fyMin;

    /**
     * 飞羽区间最大值
     */
    @Column(name = "fy_max")
    private Double fyMax;

    /**
     * 权重
     */
    private Double weight;

    /**
     * 分配比例
     */
    @Column(name = "allot_percent")
    private Double allotPercent;

    /**
     * 稀释比例
     */
    @Column(name = "dilut_percent")
    private Double dilutPercent;

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

    /**
     * 总分配描述
     */
    @Column(name = "allot_desc")
    private String allotDesc;

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
     * 获取R积分池
     *
     * @return rpool_id - R积分池
     */
    public Integer getRpoolId() {
        return rpoolId;
    }

    /**
     * 设置R积分池
     *
     * @param rpoolId R积分池
     */
    public void setRpoolId(Integer rpoolId) {
        this.rpoolId = rpoolId;
    }

    /**
     * 获取飞羽区间最小值
     *
     * @return fy_min - 飞羽区间最小值
     */
    public Double getFyMin() {
        return fyMin;
    }

    /**
     * 设置飞羽区间最小值
     *
     * @param fyMin 飞羽区间最小值
     */
    public void setFyMin(Double fyMin) {
        this.fyMin = fyMin;
    }

    /**
     * 获取飞羽区间最大值
     *
     * @return fy_max - 飞羽区间最大值
     */
    public Double getFyMax() {
        return fyMax;
    }

    /**
     * 设置飞羽区间最大值
     *
     * @param fyMax 飞羽区间最大值
     */
    public void setFyMax(Double fyMax) {
        this.fyMax = fyMax;
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * 获取分配比例
     *
     * @return allot_percent - 分配比例
     */
    public Double getAllotPercent() {
        return allotPercent;
    }

    /**
     * 设置分配比例
     *
     * @param allotPercent 分配比例
     */
    public void setAllotPercent(Double allotPercent) {
        this.allotPercent = allotPercent;
    }

    /**
     * 获取稀释比例
     *
     * @return dilut_percent - 稀释比例
     */
    public Double getDilutPercent() {
        return dilutPercent;
    }

    /**
     * 设置稀释比例
     *
     * @param dilutPercent 稀释比例
     */
    public void setDilutPercent(Double dilutPercent) {
        this.dilutPercent = dilutPercent;
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
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取总分配描述
     *
     * @return allot_desc - 总分配描述
     */
    public String getAllotDesc() {
        return allotDesc;
    }

    /**
     * 设置总分配描述
     *
     * @param allotDesc 总分配描述
     */
    public void setAllotDesc(String allotDesc) {
        this.allotDesc = allotDesc;
    }
}