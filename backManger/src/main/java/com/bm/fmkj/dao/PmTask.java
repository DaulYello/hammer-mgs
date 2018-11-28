package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pm_task")
public class PmTask {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务目标
     */
    @Column(name = "task_target")
    private String taskTarget;

    /**
     * 二级描述
     */
    @Column(name = "sub_desc")
    private String subDesc;

    /**
     * 任务奖励
     */
    private Double reward;

    /**
     * 审核周期
     */
    @Column(name = "audit_cycle")
    private String auditCycle;

    /**
     * 是否需要下载APP:0,不需要;1、需要
     */
    private Byte type;

    /**
     * 打开地址
     */
    @Column(name = "down_url")
    private String downUrl;

    /**
     * 任务开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 任务结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 任务更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 状态0、正常;-1、已删除
     */
    private Integer status;

    /**
     * 任务标题图片
     */
    @Column(name = "logoId")
    private Integer logoid;

    /**
     * 详情图片
     */
    @Column(name = "imageId")
    private Integer imageid;

    private Integer proportion;

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

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
     * 获取任务标题
     *
     * @return title - 任务标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置任务标题
     *
     * @param title 任务标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取任务目标
     *
     * @return task_target - 任务目标
     */
    public String getTaskTarget() {
        return taskTarget;
    }

    /**
     * 设置任务目标
     *
     * @param taskTarget 任务目标
     */
    public void setTaskTarget(String taskTarget) {
        this.taskTarget = taskTarget;
    }

    /**
     * 获取二级描述
     *
     * @return sub_desc - 二级描述
     */
    public String getSubDesc() {
        return subDesc;
    }

    /**
     * 设置二级描述
     *
     * @param subDesc 二级描述
     */
    public void setSubDesc(String subDesc) {
        this.subDesc = subDesc;
    }

    /**
     * 获取任务奖励
     *
     * @return reward - 任务奖励
     */
    public Double getReward() {
        return reward;
    }

    /**
     * 设置任务奖励
     *
     * @param reward 任务奖励
     */
    public void setReward(Double reward) {
        this.reward = reward;
    }

    /**
     * 获取审核周期
     *
     * @return audit_cycle - 审核周期
     */
    public String getAuditCycle() {
        return auditCycle;
    }

    /**
     * 设置审核周期
     *
     * @param auditCycle 审核周期
     */
    public void setAuditCycle(String auditCycle) {
        this.auditCycle = auditCycle;
    }

    /**
     * 获取是否需要下载APP:0,不需要;1、需要
     *
     * @return type - 是否需要下载APP:0,不需要;1、需要
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置是否需要下载APP:0,不需要;1、需要
     *
     * @param type 是否需要下载APP:0,不需要;1、需要
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取打开地址
     *
     * @return down_url - 打开地址
     */
    public String getDownUrl() {
        return downUrl;
    }

    /**
     * 设置打开地址
     *
     * @param downUrl 打开地址
     */
    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    /**
     * 获取任务开始时间
     *
     * @return start_date - 任务开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置任务开始时间
     *
     * @param startDate 任务开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取任务结束时间
     *
     * @return end_date - 任务结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置任务结束时间
     *
     * @param endDate 任务结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * 获取任务更新时间
     *
     * @return update_date - 任务更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置任务更新时间
     *
     * @param updateDate 任务更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取状态0、正常;-1、已删除
     *
     * @return status - 状态0、正常;-1、已删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态0、正常;-1、已删除
     *
     * @param status 状态0、正常;-1、已删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取任务标题图片
     *
     * @return logoId - 任务标题图片
     */
    public Integer getLogoid() {
        return logoid;
    }

    /**
     * 设置任务标题图片
     *
     * @param logoid 任务标题图片
     */
    public void setLogoid(Integer logoid) {
        this.logoid = logoid;
    }

    /**
     * 获取详情图片
     *
     * @return imageId - 详情图片
     */
    public Integer getImageid() {
        return imageid;
    }

    /**
     * 设置详情图片
     *
     * @param imageid 详情图片
     */
    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }
}