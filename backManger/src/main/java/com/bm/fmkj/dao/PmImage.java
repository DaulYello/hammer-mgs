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
    @Column(name = "path")
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

}