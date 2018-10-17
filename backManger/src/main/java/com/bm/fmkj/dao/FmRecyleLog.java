package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_recyle_log")
public class FmRecyleLog {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 收取\回收CNT或R积分数量
     */
    @Column(name = "take_num")
    private Double takeNum;

    @Column(name = "friend_id")
    private Integer friendId;

    @Column(name = "take_msg")
    private String takeMsg;

    /**
     * 收取\回收时间
     */
    @Column(name = "take_date")
    private Date takeDate;

    /**
     * 收取类型:0、用户收取;1、定时任务回收;2、释放CNT时没有用户回收
     */
    @Column(name = "take_type")
    private Integer takeType;

    /**
     * 所属类型1、CNT; 2、R积分
     */
    @Column(name = "recyle_type")
    private Integer recyleType;

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getTakeMsg() {
        return takeMsg;
    }

    public void setTakeMsg(String takeMsg) {
        this.takeMsg = takeMsg;
    }

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
     * 获取收取\回收CNT或R积分数量
     *
     * @return take_num - 收取\回收CNT或R积分数量
     */
    public Double getTakeNum() {
        return takeNum;
    }

    /**
     * 设置收取\回收CNT或R积分数量
     *
     * @param takeNum 收取\回收CNT或R积分数量
     */
    public void setTakeNum(Double takeNum) {
        this.takeNum = takeNum;
    }

    /**
     * 获取收取\回收时间
     *
     * @return take_date - 收取\回收时间
     */
    public Date getTakeDate() {
        return takeDate;
    }

    /**
     * 设置收取\回收时间
     *
     * @param takeDate 收取\回收时间
     */
    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    /**
     * 获取收取类型:0、用户收取;1、定时任务回收;2、释放CNT时没有用户回收
     *
     * @return take_type - 收取类型:0、用户收取;1、定时任务回收;2、释放CNT时没有用户回收
     */
    public Integer getTakeType() {
        return takeType;
    }

    public void setTakeType(Integer takeType) {
        this.takeType = takeType;
    }

    /**
     * 获取所属类型1、CNT; 2、R积分
     *
     * @return recyle_type - 所属类型1、CNT; 2、R积分
     */
    public Integer getRecyleType() {
        return recyleType;
    }

    public void setRecyleType(Integer recyleType) {
        this.recyleType = recyleType;
    }
}