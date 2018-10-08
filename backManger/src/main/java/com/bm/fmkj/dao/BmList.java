package com.bm.fmkj.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "bm_list")
public class BmList {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 用户id--hc_account
     */
    private Integer uid;

    /**
     * 名单状态  0无  1白名单  2黑名单
     */
    private Integer status;

    /**
     * 黑名单限制用户登录APP，0可以登录，1，不能登录
     */
    @Column(name = "limit_login")
    private Integer limitLogin;
    
    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id--hc_account
     *
     * @return uid - 用户id--hc_account
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id--hc_account
     *
     * @param uid 用户id--hc_account
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取名单状态  0无  1白名单  2黑名单
     *
     * @return status - 名单状态  0无  1白名单  2黑名单
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置名单状态  0无  1白名单  2黑名单
     *
     * @param status 名单状态  0无  1白名单  2黑名单
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 黑名单限制用户登录APP，0可以登录，1，不能登录
     * @param limitLogin 0可以登录，1，不能登录
     */
	public Integer getLimitLogin() {
		return limitLogin;
	}
	
	/**
     * 黑名单限制用户登录APP，0可以登录，1，不能登录
     * @param limitLogin 0可以登录，1，不能登录
     */
	public void setLimitLogin(Integer limitLogin) {
		this.limitLogin = limitLogin;
	}
    
    
}