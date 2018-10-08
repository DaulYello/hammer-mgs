package com.bm.fmkj.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.bm.fmkj.aspectj.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 定时任务调度日志信息 sys_job_log
 * 
 * @author youxun
 */
@Table(name = "sys_job_log")
public class JobLog{
    /** ID */
    @Excel(name = "日志序号")
    @Column(name = "job_log_id")
    private Long jobLogId;

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

    /** 日志信息 */
    @Excel(name = "日志信息")
    @Column(name = "job_message")
    private String jobMessage;

    /** 执行状态（0正常 1失败） */
    @Excel(name = "执行状态")
    private String status;

    /** 异常信息 */
    @Excel(name = "异常信息")
    @Column(name = "exception_info")
    private String exceptionInfo;
    
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

    public Long getJobLogId()
    {
        return jobLogId;
    }

    public void setJobLogId(Long jobLogId)
    {
        this.jobLogId = jobLogId;
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

    public String getJobMessage()
    {
        return jobMessage;
    }

    public void setJobMessage(String jobMessage)
    {
        this.jobMessage = jobMessage;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getExceptionInfo()
    {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo)
    {
        this.exceptionInfo = exceptionInfo;
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

	@Override
    public String toString()
    {
        return "JobLog [jobLogId=" + jobLogId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", methodName="
                + methodName + ", methodParams=" + methodParams + ", jobMessage=" + jobMessage + ", status=" + status
                + ", exceptionInfo=" + exceptionInfo + "]";
    }

}
