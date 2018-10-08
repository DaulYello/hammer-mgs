package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_operate_log")
public class SysOperateLog {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 系统模块
     */
    private String module;

    /**
     * 操作人用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作描述
     */
    @Column(name = "operate_desc")
    private String operateDesc;

    /**
     * 异常信息描述
     */
    @Column(name = "exception_msg")
    private String exceptionMsg;

    /**
     * 操作状态1、成功;2、失败
     */
    @Column(name = "operate_status")
    private Long operateStatus;

    /**
     * 请求方法
     */
    @Column(name = "request_method")
    private String requestMethod;

    /**
     * 请求参数
     */
    @Column(name = "request_params")
    private String requestParams;

    /**
     * 主机
     */
    @Column(name = "request_host")
    private String requestHost;

    /**
     * 类名
     */
    @Column(name = "request_class")
    private String requestClass;

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
     * 获取系统模块
     *
     * @return module - 系统模块
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置系统模块
     *
     * @param module 系统模块
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * 获取操作人用户ID
     *
     * @return user_id - 操作人用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置操作人用户ID
     *
     * @param userId 操作人用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取操作时间
     *
     * @return create_time - 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作时间
     *
     * @param createTime 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取操作描述
     *
     * @return operate_desc - 操作描述
     */
    public String getOperateDesc() {
        return operateDesc;
    }

    /**
     * 设置操作描述
     *
     * @param operateDesc 操作描述
     */
    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    /**
     * 获取异常信息描述
     *
     * @return exception_msg - 异常信息描述
     */
    public String getExceptionMsg() {
        return exceptionMsg;
    }

    /**
     * 设置异常信息描述
     *
     * @param exceptionMsg 异常信息描述
     */
    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    /**
     * 获取操作状态1、成功;2、失败
     *
     * @return operate_status - 操作状态1、成功;2、失败
     */
    public Long getOperateStatus() {
        return operateStatus;
    }

    /**
     * 设置操作状态1、成功;2、失败
     *
     * @param operateStatus 操作状态1、成功;2、失败
     */
    public void setOperateStatus(Long operateStatus) {
        this.operateStatus = operateStatus;
    }

    /**
     * 获取请求方法
     *
     * @return request_method - 请求方法
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * 设置请求方法
     *
     * @param requestMethod 请求方法
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * 获取请求参数
     *
     * @return request_params - 请求参数
     */
    public String getRequestParams() {
        return requestParams;
    }

    /**
     * 设置请求参数
     *
     * @param requestParams 请求参数
     */
    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    /**
     * 获取主机
     *
     * @return request_host - 主机
     */
    public String getRequestHost() {
        return requestHost;
    }

    /**
     * 设置主机
     *
     * @param requestHost 主机
     */
    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    /**
     * 获取类名
     *
     * @return request_class - 类名
     */
    public String getRequestClass() {
        return requestClass;
    }

    /**
     * 设置类名
     *
     * @param requestClass 类名
     */
    public void setRequestClass(String requestClass) {
        this.requestClass = requestClass;
    }
}