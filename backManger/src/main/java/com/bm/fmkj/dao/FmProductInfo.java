package com.bm.fmkj.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "fm_product_info")
public class FmProductInfo {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 商品编号
     */
    @Column(name = "product_no")
    private String productNo;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品单价
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 出售数量
     */
    @Column(name = "product_sum")
    private Double productSum;

    /**
     * 库存
     */
    @Column(name = "product_stock")
    private Double productStock;

    /**
     * 商品图标
     */
    @Column(name = "product_icon")
    private String productIcon;

    /**
     * 商品状态0、新建状态; 1、正常; 2、下架; -1、删除
     */
    private Byte status;

    /**
     * 类目类型1、P能量
     */
    @Column(name = "category_type")
    private Byte categoryType;

    /**
     * 商品创建人
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 商品描述
     */
    @Column(name = "product_detail")
    private String productDetail;

    /**
     * 商品类型1、卖出;2、买入
     */
    @Column(name = "product_type")
    private Byte productType;

    /**
     * 币种1、人民币
     */
    private Byte currency;

    /**
     * 最高可成交的价格
     */
    @Column(name = "max_price")
    private BigDecimal maxPrice;

    /**
     * 每一笔交易额的最小限额
     */
    @Column(name = "min_limit")
    private BigDecimal minLimit;

    /**
     * 每一笔交易额的最大限额
     */
    @Column(name = "max_limit")
    private BigDecimal maxLimit;

    /**
     * 付款期限、默认15分钟
     */
    @Column(name = "payment_term")
    private Byte paymentTerm;


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
     * 获取商品编号
     *
     * @return product_no - 商品编号
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置商品编号
     *
     * @param productNo 商品编号
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * 获取商品名称
     *
     * @return product_name - 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置商品名称
     *
     * @param productName 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取商品单价
     *
     * @return product_price - 商品单价
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 设置商品单价
     *
     * @param productPrice 商品单价
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 获取出售数量
     *
     * @return product_sum - 出售数量
     */
    public Double getProductSum() {
        return productSum;
    }

    /**
     * 设置出售数量
     *
     * @param productSum 出售数量
     */
    public void setProductSum(Double productSum) {
        this.productSum = productSum;
    }

    /**
     * 获取库存
     *
     * @return product_stock - 库存
     */
    public Double getProductStock() {
        return productStock;
    }

    /**
     * 设置库存
     *
     * @param productStock 库存
     */
    public void setProductStock(Double productStock) {
        this.productStock = productStock;
    }

    /**
     * 获取商品图标
     *
     * @return product_icon - 商品图标
     */
    public String getProductIcon() {
        return productIcon;
    }

    /**
     * 设置商品图标
     *
     * @param productIcon 商品图标
     */
    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    /**
     * 获取商品状态0、新建状态; 1、正常; 2、下架; -1、删除
     *
     * @return status - 商品状态0、新建状态; 1、正常; 2、下架; -1、删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置商品状态0、新建状态; 1、正常; 2、下架; -1、删除
     *
     * @param status 商品状态0、新建状态; 1、正常; 2、下架; -1、删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取类目类型1、P能量
     *
     * @return category_type - 类目类型1、P能量
     */
    public Byte getCategoryType() {
        return categoryType;
    }

    /**
     * 设置类目类型1、P能量
     *
     * @param categoryType 类目类型1、P能量
     */
    public void setCategoryType(Byte categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * 获取商品创建人
     *
     * @return user_id - 商品创建人
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置商品创建人
     *
     * @param userId 商品创建人
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取商品描述
     *
     * @return product_detail - 商品描述
     */
    public String getProductDetail() {
        return productDetail;
    }

    /**
     * 设置商品描述
     *
     * @param productDetail 商品描述
     */
    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    /**
     * 获取商品类型1、卖出;2、买入
     *
     * @return product_type - 商品类型1、卖出;2、买入
     */
    public Byte getProductType() {
        return productType;
    }

    /**
     * 设置商品类型1、卖出;2、买入
     *
     * @param productType 商品类型1、卖出;2、买入
     */
    public void setProductType(Byte productType) {
        this.productType = productType;
    }

    /**
     * 获取币种1、人民币
     *
     * @return currency - 币种1、人民币
     */
    public Byte getCurrency() {
        return currency;
    }

    /**
     * 设置币种1、人民币
     *
     * @param currency 币种1、人民币
     */
    public void setCurrency(Byte currency) {
        this.currency = currency;
    }

    /**
     * 获取最高可成交的价格
     *
     * @return max_price - 最高可成交的价格
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置最高可成交的价格
     *
     * @param maxPrice 最高可成交的价格
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 获取每一笔交易额的最小限额
     *
     * @return min_limit - 每一笔交易额的最小限额
     */
    public BigDecimal getMinLimit() {
        return minLimit;
    }

    /**
     * 设置每一笔交易额的最小限额
     *
     * @param minLimit 每一笔交易额的最小限额
     */
    public void setMinLimit(BigDecimal minLimit) {
        this.minLimit = minLimit;
    }

    /**
     * 获取每一笔交易额的最大限额
     *
     * @return max_limit - 每一笔交易额的最大限额
     */
    public BigDecimal getMaxLimit() {
        return maxLimit;
    }

    /**
     * 设置每一笔交易额的最大限额
     *
     * @param maxLimit 每一笔交易额的最大限额
     */
    public void setMaxLimit(BigDecimal maxLimit) {
        this.maxLimit = maxLimit;
    }

    /**
     * 获取付款期限、默认15分钟
     *
     * @return payment_term - 付款期限、默认15分钟
     */
    public Byte getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * 设置付款期限、默认15分钟
     *
     * @param paymentTerm 付款期限、默认15分钟
     */
    public void setPaymentTerm(Byte paymentTerm) {
        this.paymentTerm = paymentTerm;
    }


}