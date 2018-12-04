package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hc_apkversion")
public class HcApkversion {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * apk安装包再服务器上的文件夹地址
     */
    @Column(name = "apk_url")
    private String apkUrl;

    /**
     * apk安装包的版本号
     */
    @Column(name = "apk_version")
    private String apkVersion;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 升级的内容说明
     */
    @Column(name = "upgrade_msg")
    private String upgradeMsg;

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
     * 获取apk安装包再服务器上的文件夹地址
     *
     * @return apk_url - apk安装包再服务器上的文件夹地址
     */
    public String getApkUrl() {
        return apkUrl;
    }

    /**
     * 设置apk安装包再服务器上的文件夹地址
     *
     * @param apkUrl apk安装包再服务器上的文件夹地址
     */
    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    /**
     * 获取apk安装包的版本号
     *
     * @return apk_version - apk安装包的版本号
     */
    public String getApkVersion() {
        return apkVersion;
    }

    /**
     * 设置apk安装包的版本号
     *
     * @param apkVersion apk安装包的版本号
     */
    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取升级的内容说明
     *
     * @return upgrade_msg - 升级的内容说明
     */
    public String getUpgradeMsg() {
        return upgradeMsg;
    }

    /**
     * 设置升级的内容说明
     *
     * @param upgradeMsg 升级的内容说明
     */
    public void setUpgradeMsg(String upgradeMsg) {
        this.upgradeMsg = upgradeMsg;
    }
}