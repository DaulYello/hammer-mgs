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

    /**
     * 收取\回收时间
     */
    @Column(name = "take_date")
    private Date takeDate;

    /**
     * 收取类型:0、用户收取;1、定时任务回收;2、释放CNT时没有用户回收
     */
    @Column(name = "take_type")
    private Byte takeType;

    /**
     * 所属类型1、CNT; 2、R积分
     */
    @Column(name = "recyle_type")
    private Byte recyleType;

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
    public Byte getTakeType() {
        return takeType;
    }

    /**
     * 设置收取类型:0、用户收取;1、定时任务回收;2、释放CNT时没有用户回收
     *
     * @param takeType 收取类型:0、用户收取;1、定时任务回收;2、释放CNT时没有用户回收
     */
    public void setTakeType(Byte takeType) {
        this.takeType = takeType;
    }

    /**
     * 获取所属类型1、CNT; 2、R积分
     *
     * @return recyle_type - 所属类型1、CNT; 2、R积分
     */
    public Byte getRecyleType() {
        return recyleType;
    }

    /**
     * 设置所属类型1、CNT; 2、R积分
     *
     * @param recyleType 所属类型1、CNT; 2、R积分
     */
    public void setRecyleType(Byte recyleType) {
        this.recyleType = recyleType;
    }
}