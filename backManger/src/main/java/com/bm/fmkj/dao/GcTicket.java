package com.bm.fmkj.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_ticket")
public class GcTicket {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 门票的类型
     */
    private String type;

    /**
     * 门票的名字
     */
    private String name;

    /**
     * 门票对应的单价
     */
    private Double price;

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
     * 获取门票的类型
     *
     * @return type - 门票的类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置门票的类型
     *
     * @param type 门票的类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取门票的名字
     *
     * @return name - 门票的名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置门票的名字
     *
     * @param name 门票的名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取门票对应的单价
     *
     * @return price - 门票对应的单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置门票对应的单价
     *
     * @param price 门票对应的单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}