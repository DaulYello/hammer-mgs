package com.bm.fmkj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.bm.fmkj.aspectj.Excel;
import com.bm.fmkj.job.ScheduleConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 定时任务调度信息 sys_job
 * 
 * @author youxun
 */
@Table(name = "sys_job")
public class Job implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 任务ID */
    @Excel(name = "任务序号")
    @Column(name = "job_id")
    private Long jobId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    @Column(name = "job_name")
    private String jobName;

    /** 任务组名 */
    @Excel(name = "任务组名")
    @Column(name = "job_group")
    private String jobGroup;

    /** 任务方法 */
    @Excel(name = "任务方法")
    @Column(name = "method_name")
    private String methodName;

    /** 方法参数 */
    @Excel(name = "方法参数")
    @Column(name = "method_params")
    private String methodParams;

    /** cron执行表达式 */
    @Excel(name = "执行表达式 ")
    @Column(name = "cron_expression")
    private String cronExpression;

    /** cron计划策略 */
    @Excel(name = "计划策略 ")
    @Column(name = "misfire_policy")
    private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

    /** 任务状态（0正常 1暂停） */
    @Excel(name = "任务状态")
    private String status;
    
    /** 创建者 */
    @Column(name = "create_by")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /** 更新者 */
    @Column(name = "update_by")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    /** 备注 */
    private String remark;

    public Long getJobId()
    {
        return jobId;
    }

    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    public String getMethodParams()
    {
        return methodParams;
    }

    public void setMethodParams(String methodParams)
    {
        this.methodParams = methodParams;
    }

    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    public String getMisfirePolicy()
    {
        return misfirePolicy;
    }

    public void setMisfirePolicy(String misfirePolicy)
    {
        this.misfirePolicy = misfirePolicy;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
    public String toString()
    {
        return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", methodName=" + methodName
                + ", methodParams=" + methodParams + ", cronExpression=" + cronExpression + ", status=" + status + "]";
    }

}
