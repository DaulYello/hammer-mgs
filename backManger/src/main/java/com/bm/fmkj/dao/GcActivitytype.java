package com.bm.fmkj.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_activitytype")
public class GcActivitytype {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 活动的类型
     */
    private String type;

    /**
     * 持续天数
     */
    private Integer days;


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
     * 获取活动的类型
     *
     * @return type - 活动的类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置活动的类型
     *
     * @param type 活动的类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取持续天数
     *
     * @return days - 持续天数
     */
    public Integer getDays() {
        return days;
    }

    /**
     * 设置持续天数
     *
     * @param days 持续天数
     */
    public void setDays(Integer days) {
        this.days = days;
    }
}