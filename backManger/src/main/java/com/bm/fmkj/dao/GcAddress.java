package com.bm.fmkj.dao;

import javax.persistence.*;

@Table(name = "gc_address")
public class GcAddress {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人电话
     */
    private String telephone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 初始为0,中奖用户不可修改地址
     */
    private Integer lock;

    /**
     * 用户默认地址
     */
    private Integer status;

    /**
     * 收货人详细地址
     */
    private String address;

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
     * 获取收货人姓名
     *
     * @return name - 收货人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收货人姓名
     *
     * @param name 收货人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取收货人电话
     *
     * @return telephone - 收货人电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置收货人电话
     *
     * @param telephone 收货人电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return county - 区
     */
    public String getCounty() {
        return county;
    }

    /**
     * 设置区
     *
     * @param county 区
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * 获取初始为0,中奖用户不可修改地址
     *
     * @return lock - 初始为0,中奖用户不可修改地址
     */
    public Integer getLock() {
        return lock;
    }

    /**
     * 设置初始为0,中奖用户不可修改地址
     *
     * @param lock 初始为0,中奖用户不可修改地址
     */
    public void setLock(Integer lock) {
        this.lock = lock;
    }

    /**
     * 获取用户默认地址
     *
     * @return status - 用户默认地址
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用户默认地址
     *
     * @param status 用户默认地址
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取收货人详细地址
     *
     * @return address - 收货人详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收货人详细地址
     *
     * @param address 收货人详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
}