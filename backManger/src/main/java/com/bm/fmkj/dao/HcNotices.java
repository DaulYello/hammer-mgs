package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hc_notices")
public class HcNotices {
    @Id
    private Integer id;

    private String title;

    private Date time;

    /**
     * 标示发布的公告是否通过admin验证，通过为1，不通过为0，默认为0
     */
    private Integer pass;

    private String contents;

    /**
     * @return id
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取标示发布的公告是否通过admin验证，通过为1，不通过为0，默认为0
     *
     * @return pass - 标示发布的公告是否通过admin验证，通过为1，不通过为0，默认为0
     */
    public Integer getPass() {
        return pass;
    }

    /**
     * 设置标示发布的公告是否通过admin验证，通过为1，不通过为0，默认为0
     *
     * @param pass 标示发布的公告是否通过admin验证，通过为1，不通过为0，默认为0
     */
    public void setPass(Integer pass) {
        this.pass = pass;
    }

    /**
     * @return contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }
}