package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hc_userimage")
public class HcUserImage {
    @Id
    @Column(name = "Id")
    private Integer id;

    private Integer uid;

    /**
     * 照片路径
     */
    private String url;

    /**
     * 正面照
     */
    @Column(name = "full_photo")
    private String fullPhoto;

    /**
     * 反面照片
     */
    @Column(name = "reverse_photo")
    private String reversePhoto;

    /**
     * 存储时间
     */
    private Date time;

    /**
     * 审核状态--0.未审核 1.通过 2.未通过3.实名认证未通过
     */
    private Integer status;

    /**
     * 支付宝账号
     */
    @Column(name = "alipay_account")
    private String alipayAccount;

    /**
     * 支付宝的收款码照片
     */
    @Column(name = "alipay_photo")
    private String alipayPhoto;

    /**
     * 微信账号
     */
    @Column(name = "wechat_account")
    private String wechatAccount;

    /**
     * 微信的收款码照片
     */
    @Column(name = "wechat_photo")
    private String wechatPhoto;

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
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取照片路径
     *
     * @return url - 照片路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置照片路径
     *
     * @param url 照片路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取正面照
     *
     * @return full_photo - 正面照
     */
    public String getFullPhoto() {
        return fullPhoto;
    }

    /**
     * 设置正面照
     *
     * @param fullPhoto 正面照
     */
    public void setFullPhoto(String fullPhoto) {
        this.fullPhoto = fullPhoto;
    }

    /**
     * 获取反面照片
     *
     * @return reverse_photo - 反面照片
     */
    public String getReversePhoto() {
        return reversePhoto;
    }

    /**
     * 设置反面照片
     *
     * @param reversePhoto 反面照片
     */
    public void setReversePhoto(String reversePhoto) {
        this.reversePhoto = reversePhoto;
    }

    /**
     * 获取存储时间
     *
     * @return time - 存储时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置存储时间
     *
     * @param time 存储时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取审核状态--0.未审核 1.通过 2.未通过3.实名认证未通过
     *
     * @return status - 审核状态--0.未审核 1.通过 2.未通过3.实名认证未通过
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置审核状态--0.未审核 1.通过 2.未通过3.实名认证未通过
     *
     * @param status 审核状态--0.未审核 1.通过 2.未通过3.实名认证未通过
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取支付宝账号
     *
     * @return alipay_account - 支付宝账号
     */
    public String getAlipayAccount() {
        return alipayAccount;
    }

    /**
     * 设置支付宝账号
     *
     * @param alipayAccount 支付宝账号
     */
    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    /**
     * 获取支付宝的收款码照片
     *
     * @return alipay_photo - 支付宝的收款码照片
     */
    public String getAlipayPhoto() {
        return alipayPhoto;
    }

    /**
     * 设置支付宝的收款码照片
     *
     * @param alipayPhoto 支付宝的收款码照片
     */
    public void setAlipayPhoto(String alipayPhoto) {
        this.alipayPhoto = alipayPhoto;
    }

    /**
     * 获取微信账号
     *
     * @return wechat_account - 微信账号
     */
    public String getWechatAccount() {
        return wechatAccount;
    }

    /**
     * 设置微信账号
     *
     * @param wechatAccount 微信账号
     */
    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    /**
     * 获取微信的收款码照片
     *
     * @return wechat_photo - 微信的收款码照片
     */
    public String getWechatPhoto() {
        return wechatPhoto;
    }

    /**
     * 设置微信的收款码照片
     *
     * @param wechatPhoto 微信的收款码照片
     */
    public void setWechatPhoto(String wechatPhoto) {
        this.wechatPhoto = wechatPhoto;
    }
}