package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pm_image")
public class PmImage {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 外键
     */
    private Integer tid;

    /**
     * 图片类型1、任务头像;2、任务详情图片;3、审核图片
     */
    @Column(name = "image_type")
    private Byte imageType;

    /**
     * 图片地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 上传时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

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
     * 获取外键
     *
     * @return tid - 外键
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置外键
     *
     * @param tid 外键
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 获取图片类型1、任务头像;2、任务详情图片;3、审核图片
     *
     * @return image_type - 图片类型1、任务头像;2、任务详情图片;3、审核图片
     */
    public Byte getImageType() {
        return imageType;
    }

    /**
     * 设置图片类型1、任务头像;2、任务详情图片;3、审核图片
     *
     * @param imageType 图片类型1、任务头像;2、任务详情图片;3、审核图片
     */
    public void setImageType(Byte imageType) {
        this.imageType = imageType;
    }

    /**
     * 获取图片地址
     *
     * @return image_url - 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imageUrl 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取上传时间
     *
     * @return create_date - 上传时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置上传时间
     *
     * @param createDate 上传时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}