package com.bm.fmkj.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_joinactivityrecord")
public class GcJoinactivityrecord {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 参与活动的用户id
     */
    private Integer uid;

    /**
     * 对应活动表activity中的主键
     */
    private Integer aid;

    /**
     * 对应ticket表中的主键
     */
    private Integer ticketid;

    /**
     * 参与活动的时间
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
     * 获取参与活动的用户id
     *
     * @return uid - 参与活动的用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置参与活动的用户id
     *
     * @param uid 参与活动的用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取对应活动表activity中的主键
     *
     * @return aid - 对应活动表activity中的主键
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * 设置对应活动表activity中的主键
     *
     * @param aid 对应活动表activity中的主键
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    /**
     * 获取对应ticket表中的主键
     *
     * @return ticketid - 对应ticket表中的主键
     */
    public Integer getTicketid() {
        return ticketid;
    }

    /**
     * 设置对应ticket表中的主键
     *
     * @param ticketid 对应ticket表中的主键
     */
    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    /**
     * 获取参与活动的时间
     *
     * @return time - 参与活动的时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置参与活动的时间
     *
     * @param time 参与活动的时间
     */
    public void setTime(Date time) {
        this.time = time;
    }
}