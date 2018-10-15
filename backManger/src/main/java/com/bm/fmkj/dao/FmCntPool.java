package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_cnt_pool")
public class FmCntPool {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * cnt释放资金池、由定时任务每天按收益比例注入
     */
    @Column(name = "cnt_pool")
    private Double cntPool;

    /**
     * 当天没用户收取回收了多少,真正回收的值需要入公司账户
     */
    @Column(name = "cnt_recyle")
    private Double cntRecyle;

    /**
     * 生成时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 回收时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 年度、方便统计
     */
    private Integer year;

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
     * 获取cnt释放资金池、由定时任务每天按收益比例注入
     *
     * @return cnt_pool - cnt释放资金池、由定时任务每天按收益比例注入
     */
    public Double getCntPool() {
        return cntPool;
    }

    /**
     * 设置cnt释放资金池、由定时任务每天按收益比例注入
     *
     * @param cntPool cnt释放资金池、由定时任务每天按收益比例注入
     */
    public void setCntPool(Double cntPool) {
        this.cntPool = cntPool;
    }

    /**
     * 获取当天没用户收取回收了多少,真正回收的值需要入公司账户
     *
     * @return cnt_recyle - 当天没用户收取回收了多少,真正回收的值需要入公司账户
     */
    public Double getCntRecyle() {
        return cntRecyle;
    }

    /**
     * 设置当天没用户收取回收了多少,真正回收的值需要入公司账户
     *
     * @param cntRecyle 当天没用户收取回收了多少,真正回收的值需要入公司账户
     */
    public void setCntRecyle(Double cntRecyle) {
        this.cntRecyle = cntRecyle;
    }

    /**
     * 获取生成时间
     *
     * @return create_date - 生成时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置生成时间
     *
     * @param createDate 生成时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取回收时间
     *
     * @return update_date - 回收时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置回收时间
     *
     * @param updateDate 回收时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取年度、方便统计
     *
     * @return year - 年度、方便统计
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 设置年度、方便统计
     *
     * @param year 年度、方便统计
     */
    public void setYear(Integer year) {
        this.year = year;
    }
}