package com.bm.fmkj.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_pimage")
public class GcPimage {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 活动id
     */
    private Integer aid;

    /**
     * 产品图片
     */
    private String imageurl;

    /**
     * 图片标识
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

    /**
     * 获取产品图片
     *
     * @return imageurl - 产品图片
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * 设置产品图片
     *
     * @param imageurl 产品图片
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     * 获取图片标识
     *
     * @return flag - 图片标识
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 设置图片标识
     *
     * @param flag 图片标识
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}