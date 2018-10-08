package com.bm.fmkj.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hc_points_record")
public class HcPointsRecord {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 对应hc_points_class表中的主键
     */
    @Column(name = "points_id")
    private Integer pointsId;

    /**
     * 得到的积分数
     */
    @Column(name = "points_num")
    private Double pointsNum;

    /**
     * 时间
     */
    private Date time;

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
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取对应hc_points_class表中的主键
     *
     * @return points_id - 对应hc_points_class表中的主键
     */
    public Integer getPointsId() {
        return pointsId;
    }

    /**
     * 设置对应hc_points_class表中的主键
     *
     * @param pointsId 对应hc_points_class表中的主键
     */
    public void setPointsId(Integer pointsId) {
        this.pointsId = pointsId;
    }

    /**
     * 获取得到的积分数
     *
     * @return points_num - 得到的积分数
     */
    public Double getPointsNum() {
        return pointsNum;
    }

    /**
     * 设置得到的积分数
     *
     * @param pointsNum 得到的积分数
     */
    public void setPointsNum(Double pointsNum) {
        this.pointsNum = pointsNum;
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}