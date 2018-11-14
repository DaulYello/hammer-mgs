package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hc_account")
public class HcAccount {
    @Id
    private Integer id;

    /**
     * 国家
     */
    private String nation;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 动态码
     */
    private String dycode;

    private Date dycodetime;

    /**
     * 头像
     */
    private String logo;

    private String nickname;

    private String email;

    private String password;

    private String name;

    private String cardnum;

    private Boolean authlock;

    private Integer rid;

    /**
     * 权重：用户等级越高权重越大，主要决定了用户月亮井能量生产得快慢
     */
    @Column(name = "make_weight")
    private String makeWeight;

    /**
     * 我的资产，我有多少能量
     */
    @Column(name = "my_p")
    private Double myP;

    /**
     * 用户的积分，积分越高等级（rid）就越高
     */
    private Integer score;

    @Column(name = "grade_id")
    private Integer gradeId;

    /**
     * 0:身份未验证， 1：身份通过接口验证姓名和身份证号，2:在身份证和姓名通过验证之后上传的照片通过后台人工验证
     */
    @Column(name = "card_status")
    private Integer cardStatus;

    /**
     * 是否原住民(0:不是  1:是)
     */
    private Integer isboong;

    /**
     * 锤多宝id
     */
    private Long cdbid;

    /**
     * 实名认证未通过的原因信息
     */
    @Column(name = "realnam_info")
    private String realnamInfo;

    /**
     * 用户拥有CNT
     */
    private Double cnt;

    /**
     * 注册时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

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
     * 获取国家
     *
     * @return nation - 国家
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置国家
     *
     * @param nation 国家
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取电话号码
     *
     * @return telephone - 电话号码
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话号码
     *
     * @param telephone 电话号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取动态码
     *
     * @return dycode - 动态码
     */
    public String getDycode() {
        return dycode;
    }

    /**
     * 设置动态码
     *
     * @param dycode 动态码
     */
    public void setDycode(String dycode) {
        this.dycode = dycode;
    }

    /**
     * @return dycodetime
     */
    public Date getDycodetime() {
        return dycodetime;
    }

    /**
     * @param dycodetime
     */
    public void setDycodetime(Date dycodetime) {
        this.dycodetime = dycodetime;
    }

    /**
     * 获取头像
     *
     * @return logo - 头像
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置头像
     *
     * @param logo 头像
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return cardnum
     */
    public String getCardnum() {
        return cardnum;
    }

    /**
     * @param cardnum
     */
    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    /**
     * @return authlock
     */
    public Boolean getAuthlock() {
        return authlock;
    }

    /**
     * @param authlock
     */
    public void setAuthlock(Boolean authlock) {
        this.authlock = authlock;
    }

    /**
     * @return rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * 获取权重：用户等级越高权重越大，主要决定了用户月亮井能量生产得快慢
     *
     * @return make_weight - 权重：用户等级越高权重越大，主要决定了用户月亮井能量生产得快慢
     */
    public String getMakeWeight() {
        return makeWeight;
    }

    /**
     * 设置权重：用户等级越高权重越大，主要决定了用户月亮井能量生产得快慢
     *
     * @param makeWeight 权重：用户等级越高权重越大，主要决定了用户月亮井能量生产得快慢
     */
    public void setMakeWeight(String makeWeight) {
        this.makeWeight = makeWeight;
    }

    /**
     * 获取我的资产，我有多少能量
     *
     * @return my_p - 我的资产，我有多少能量
     */
    public Double getMyP() {
        return myP;
    }

    /**
     * 设置我的资产，我有多少能量
     *
     * @param myP 我的资产，我有多少能量
     */
    public void setMyP(Double myP) {
        this.myP = myP;
    }

    /**
     * 获取用户的积分，积分越高等级（rid）就越高
     *
     * @return score - 用户的积分，积分越高等级（rid）就越高
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置用户的积分，积分越高等级（rid）就越高
     *
     * @param score 用户的积分，积分越高等级（rid）就越高
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return grade_id
     */
    public Integer getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId
     */
    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * 获取0:身份未验证， 1：身份通过接口验证姓名和身份证号，2:在身份证和姓名通过验证之后上传的照片通过后台人工验证
     *
     * @return card_status - 0:身份未验证， 1：身份通过接口验证姓名和身份证号，2:在身份证和姓名通过验证之后上传的照片通过后台人工验证
     */
    public Integer getCardStatus() {
        return cardStatus;
    }

    /**
     * 设置0:身份未验证， 1：身份通过接口验证姓名和身份证号，2:在身份证和姓名通过验证之后上传的照片通过后台人工验证
     *
     * @param cardStatus 0:身份未验证， 1：身份通过接口验证姓名和身份证号，2:在身份证和姓名通过验证之后上传的照片通过后台人工验证
     */
    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    /**
     * 获取是否原住民(0:不是  1:是)
     *
     * @return isboong - 是否原住民(0:不是  1:是)
     */
    public Integer getIsboong() {
        return isboong;
    }

    /**
     * 设置是否原住民(0:不是  1:是)
     *
     * @param isboong 是否原住民(0:不是  1:是)
     */
    public void setIsboong(Integer isboong) {
        this.isboong = isboong;
    }

    /**
     * 获取锤多宝id
     *
     * @return cdbid - 锤多宝id
     */
    public Long getCdbid() {
        return cdbid;
    }

    /**
     * 设置锤多宝id
     *
     * @param cdbid 锤多宝id
     */
    public void setCdbid(Long cdbid) {
        this.cdbid = cdbid;
    }

    /**
     * 获取实名认证未通过的原因信息
     *
     * @return realnam_info - 实名认证未通过的原因信息
     */
    public String getRealnamInfo() {
        return realnamInfo;
    }

    /**
     * 设置实名认证未通过的原因信息
     *
     * @param realnamInfo 实名认证未通过的原因信息
     */
    public void setRealnamInfo(String realnamInfo) {
        this.realnamInfo = realnamInfo;
    }

    /**
     * 获取用户拥有CNT
     *
     * @return cnt - 用户拥有CNT
     */
    public Double getCnt() {
        return cnt;
    }

    /**
     * 设置用户拥有CNT
     *
     * @param cnt 用户拥有CNT
     */
    public void setCnt(Double cnt) {
        this.cnt = cnt;
    }

    /**
     * 获取注册时间
     *
     * @return create_date - 注册时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置注册时间
     *
     * @param createDate 注册时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}