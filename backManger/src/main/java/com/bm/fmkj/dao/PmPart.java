package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pm_part")
public class PmPart {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 参与用户
     */
    private Integer uid;

    /**
     * 任务id
     */
    private Integer tid;

    /**
     * 审核状态0,未审核;1、待审核; 2、审核通过;-1、驳回
     */
    @Column(name = "audit_status")
    private Byte auditStatus;

    /**
     * 审核意见
     */
    @Column(name = "audit_option")
    private String auditOption;

    /**
     * 提交电话
     */
    private String telephone;

    /**
     * 参与时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 审核时间
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
     * 获取参与用户
     *
     * @return uid - 参与用户
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置参与用户
     *
     * @param uid 参与用户
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取任务id
     *
     * @return tid - 任务id
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置任务id
     *
     * @param tid 任务id
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 获取审核状态0,未审核;1、待审核; 2、审核通过;-1、驳回
     *
     * @return audit_status - 审核状态0,未审核;1、待审核; 2、审核通过;-1、驳回
     */
    public Byte getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态0,未审核;1、待审核; 2、审核通过;-1、驳回
     *
     * @param auditStatus 审核状态0,未审核;1、待审核; 2、审核通过;-1、驳回
     */
    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditOption() {
        return auditOption;
    }

    public void setAuditOption(String auditOption) {
        this.auditOption = auditOption;
    }

    /**
     * 获取提交电话
     *
     * @return telephone - 提交电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置提交电话
     *
     * @param telephone 提交电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取参与时间
     *
     * @return create_date - 参与时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置参与时间
     *
     * @param createDate 参与时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取审核时间
     *
     * @return update_date - 审核时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置审核时间
     *
     * @param updateDate 审核时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}