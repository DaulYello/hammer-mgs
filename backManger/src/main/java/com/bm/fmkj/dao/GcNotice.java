package com.bm.fmkj.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_notice")
public class GcNotice {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 通知内容
     */
    private String message;

    /**
     * 通知时间
     */
    private Date time;

    /**
     * 已读标识 0已读 1未读
     */
    private Integer flag;

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
     * 获取通知内容
     *
     * @return message - 通知内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置通知内容
     *
     * @param message 通知内容
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取通知时间
     *
     * @return time - 通知时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置通知时间
     *
     * @param time 通知时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取已读标识 0已读 1未读
     *
     * @return flag - 已读标识 0已读 1未读
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置已读标识 0已读 1未读
     *
     * @param flag 已读标识 0已读 1未读
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}