package com.bm.fmkj.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_collect")
public class GcCollect {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 活动id
     */
    private Integer aid;

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
     * 获取活动id
     *
     * @return aid - 活动id
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置活动id
     *
     * @param aid 活动id
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }
}