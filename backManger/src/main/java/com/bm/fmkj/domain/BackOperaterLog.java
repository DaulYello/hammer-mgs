/*
package com.bm.fmkj.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

*/
/**
 * @function 后台管理系统的操作日志model
 * @author hunagshuang
 * @date 2018-11-05
 *//*

@Table(name = "sys_operate_log")
public class BackOperaterLog {

    @Column(name = "")
    private Integer id;

    private String module;

    @TableField("user_id")
    private Integer userId;

    @TableField("create_time")
    @TableField("create_time")
    private Date createTime;

    @TableField("operate_desc")
    private String operateDesc;

    @TableField("exception_msg")
    private String exceptionMsg;

    @TableField("operate_status")
    private Integer operateStatus;

    @TableField("request_method")
    private String requestMethod;

    @TableField("request_params")
    private String requestparams;

    @TableField("request_class")
    private String requestClass;

    @TableField("request_host")
    private String requestHost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Integer getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestparams() {
        return requestparams;
    }

    public void setRequestparams(String requestparams) {
        this.requestparams = requestparams;
    }

    public String getRequestHost() {
        return requestHost;
    }

    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    public String getRequestClass() {
        return requestClass;
    }

    public void setRequestClass(String requestClass) {
        this.requestClass = requestClass;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
*/
