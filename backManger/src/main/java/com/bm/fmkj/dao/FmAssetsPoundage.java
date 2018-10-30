package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_assets_poundage")
public class FmAssetsPoundage {
    /**
     * 主键id
     */
    @Id
    private Integer id;

    /**
     * 活动id
     */
    private Integer aid;

    /**
     * 活动溢价后的总资产
     */
    @Column(name = "total_assets")
    private Double totalAssets;

    /**
     * 手续费
     */
    private Double poundage;

    /**
     * 释放比例
     */
    @Column(name = "release_rate")
    private Double releaseRate;

    /**
     * 数据记录时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取活动id
     *
     * @return aid - 活动id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置活动id
     *
     * @param aid 活动id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取活动溢价后的总资产
     *
     * @return total_assets - 活动溢价后的总资产
     */
    public Double getTotalAssets() {
        return totalAssets;
    }

    /**
     * 设置活动溢价后的总资产
     *
     * @param totalAssets 活动溢价后的总资产
     */
    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    /**
     * 获取手续费
     *
     * @return poundage - 手续费
     */
    public Double getPoundage() {
        return poundage;
    }

    /**
     * 设置手续费
     *
     * @param poundage 手续费
     */
    public void setPoundage(Double poundage) {
        this.poundage = poundage;
    }

    /**
     * 获取释放比例
     *
     * @return release_rate - 释放比例
     */
    public Double getReleaseRate() {
        return releaseRate;
    }

    /**
     * 设置释放比例
     *
     * @param releaseRate 释放比例
     */
    public void setReleaseRate(Double releaseRate) {
        this.releaseRate = releaseRate;
    }

    /**
     * 获取数据记录时间
     *
     * @return create_date - 数据记录时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置数据记录时间
     *
     * @param createDate 数据记录时间
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
}