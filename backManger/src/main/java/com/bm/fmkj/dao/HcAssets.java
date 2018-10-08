package com.bm.fmkj.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hc_assets")
public class HcAssets {
    @Id
    private Integer id;

    /**
     * 用户的id，对应hc_account中的id
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * 钱包地址
     */
    private String walletfile;

    /**
     * 助记词备份状态0否，1是
     */
    private Integer status;

    /**
     * 资产种类,有两种HRC，ECH
     */
    private String assetskind;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * keystore文件
     */
    private String keystore;

    /**
     * @return id
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
     * 获取用户的id，对应hc_account中的id
     *
     * @return userId - 用户的id，对应hc_account中的id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户的id，对应hc_account中的id
     *
     * @param userid 用户的id，对应hc_account中的id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取钱包地址
     *
     * @return walletfile - 钱包地址
     */
    public String getWalletfile() {
        return walletfile;
    }

    /**
     * 设置钱包地址
     *
     * @param walletfile 钱包地址
     */
    public void setWalletfile(String walletfile) {
        this.walletfile = walletfile;
    }

    /**
     * 获取助记词备份状态0否，1是
     *
     * @return status - 助记词备份状态0否，1是
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置助记词备份状态0否，1是
     *
     * @param status 助记词备份状态0否，1是
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取资产种类,有两种HRC，ECH
     *
     * @return assetskind - 资产种类,有两种HRC，ECH
     */
    public String getAssetskind() {
        return assetskind;
    }

    /**
     * 设置资产种类,有两种HRC，ECH
     *
     * @param assetskind 资产种类,有两种HRC，ECH
     */
    public void setAssetskind(String assetskind) {
        this.assetskind = assetskind;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取keystore文件
     *
     * @return keystore - keystore文件
     */
    public String getKeystore() {
        return keystore;
    }

    /**
     * 设置keystore文件
     *
     * @param keystore keystore文件
     */
    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }
}