package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_integral_info")
public class FmIntegralInfo {
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
     * 每个阶段生成的R积分数量
     */
    @Column(name = "integral_num")
    private Double integralNum;

    /**
     * 0未收取状态; 1自己已收取; 2系统回收;-1被好友偷走
     */
    private Byte status;

    /**
     * R积分生成时间
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
     * 获取每个阶段生成的R积分数量
     *
     * @return integral_num - 每个阶段生成的R积分数量
     */
    public Double getIntegralNum() {
        return integralNum;
    }

    /**
     * 设置每个阶段生成的R积分数量
     *
     * @param integralNum 每个阶段生成的R积分数量
     */
    public void setIntegralNum(Double integralNum) {
        this.integralNum = integralNum;
    }

    /**
     * 获取0未收取状态; 1自己已收取; 2系统回收;-1被好友偷走
     *
     * @return status - 0未收取状态; 1自己已收取; 2系统回收;-1被好友偷走
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0未收取状态; 1自己已收取; 2系统回收;-1被好友偷走
     *
     * @param status 0未收取状态; 1自己已收取; 2系统回收;-1被好友偷走
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取R积分生成时间
     *
     * @return create_date - R积分生成时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置R积分生成时间
     *
     * @param createDate R积分生成时间
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