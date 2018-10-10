package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_rpool")
public class FmRpool {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 投放年度
     */
    private Integer year;

    /**
     * 年度R积分投放池
     */
    @Column(name = "rintegral_num")
    private Double rintegralNum;

    /**
     * 年度R积分回收池
     */
    @Column(name = "recycle_num")
    private Double recycleNum;

    /**
     * 创建时间
     */
    @Column(name = "createDate")
    private Date createdate;

    /**
     * 修改时间
     */
    @Column(name = "updateDate")
    private Date updatedate;

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
     * 获取投放年度
     *
     * @return year - 投放年度
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 设置投放年度
     *
     * @param year 投放年度
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 获取年度R积分投放池
     *
     * @return rintegral_num - 年度R积分投放池
     */
    public Double getRintegralNum() {
        return rintegralNum;
    }

    /**
     * 设置年度R积分投放池
     *
     * @param rintegralNum 年度R积分投放池
     */
    public void setRintegralNum(Double rintegralNum) {
        this.rintegralNum = rintegralNum;
    }

    /**
     * 获取年度R积分回收池
     *
     * @return recycle_num - 年度R积分回收池
     */
    public Double getRecycleNum() {
        return recycleNum;
    }

    /**
     * 设置年度R积分回收池
     *
     * @param recycleNum 年度R积分回收池
     */
    public void setRecycleNum(Double recycleNum) {
        this.recycleNum = recycleNum;
    }

    /**
     * 获取创建时间
     *
     * @return createDate - 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建时间
     *
     * @param createdate 创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取修改时间
     *
     * @return updateDate - 修改时间
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置修改时间
     *
     * @param updatedate 修改时间
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}