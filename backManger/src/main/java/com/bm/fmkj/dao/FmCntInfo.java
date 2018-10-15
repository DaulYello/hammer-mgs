package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_cnt_info")
public class FmCntInfo {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 产生CNT数量
     */
    @Column(name = "cnt_num")
    private Double cntNum;

    /**
     * CNT状态0,未收取;1已收取;-1被系统回收
     */
    private Integer status;

    /**
     * CNT产生时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
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
     * 获取用户ID
     *
     * @return uid - 用户ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户ID
     *
     * @param uid 用户ID
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取产生CNT数量
     *
     * @return cnt_num - 产生CNT数量
     */
    public Double getCntNum() {
        return cntNum;
    }

    /**
     * 设置产生CNT数量
     *
     * @param cntNum 产生CNT数量
     */
    public void setCntNum(Double cntNum) {
        this.cntNum = cntNum;
    }

    /**
     * 获取CNT状态0,未收取;1已收取;-1被系统回收
     *
     * @return status - CNT状态0,未收取;1已收取;-1被系统回收
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 获取CNT产生时间
     *
     * @return create_date - CNT产生时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置CNT产生时间
     *
     * @param createDate CNT产生时间
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