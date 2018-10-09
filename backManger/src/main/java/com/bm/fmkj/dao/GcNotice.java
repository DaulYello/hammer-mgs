package com.bm.fmkj.dao;

import javax.persistence.*;

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
     * 消息id
     */
    private Integer mid;

    /**
     * 已读标识 1未读 0已读
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
     * 获取消息id
     *
     * @return mid - 消息id
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * 设置消息id
     *
     * @param mid 消息id
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * 获取已读标识 1未读 0已读
     *
     * @return flag - 已读标识 1未读 0已读
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置已读标识 1未读 0已读
     *
     * @param flag 已读标识 1未读 0已读
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}