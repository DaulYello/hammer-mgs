package com.bm.fmkj.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_buyticketrecord")
public class GcBuyticketrecord {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 购买门票的用户id
     */
    private Integer uid;

    /**
     * 对应gc_ticket表中的主键
     */
    private Integer ticketid;

    /**
     * 持有票的数量
     */
    private Integer num;

    /**
     * 购买票的时间
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
     * 获取购买门票的用户id
     *
     * @return uid - 购买门票的用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置购买门票的用户id
     *
     * @param uid 购买门票的用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取对应gc_ticket表中的主键
     *
     * @return ticketid - 对应gc_ticket表中的主键
     */
    public Integer getTicketid() {
        return ticketid;
    }

    /**
     * 设置对应gc_ticket表中的主键
     *
     * @param ticketid 对应gc_ticket表中的主键
     */
    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    /**
     * 获取持有票的数量
     *
     * @return num - 持有票的数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置持有票的数量
     *
     * @param num 持有票的数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取购买票的时间
     *
     * @return time - 购买票的时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置购买票的时间
     *
     * @param time 购买票的时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}