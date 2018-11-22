package com.bm.fmkj.dao;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pm_prompt")
public class PmPrompt {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 任务ID
     */
    private Integer tid;

    /**
     * 内容
     */
    @Column(name = "prompt_text")
    private String promptText;

    /**
     * 显示顺序
     */
    @Column(name = "order_num")
    private Byte orderNum;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

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
     * 获取任务ID
     *
     * @return tid - 任务ID
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 设置任务ID
     *
     * @param tid 任务ID
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 获取内容
     *
     * @return prompt_text - 内容
     */
    public String getPromptText() {
        return promptText;
    }

    /**
     * 设置内容
     *
     * @param promptText 内容
     */
    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    /**
     * 获取显示顺序
     *
     * @return order_num - 显示顺序
     */
    public Byte getOrderNum() {
        return orderNum;
    }

    /**
     * 设置显示顺序
     *
     * @param orderNum 显示顺序
     */
    public void setOrderNum(Byte orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}