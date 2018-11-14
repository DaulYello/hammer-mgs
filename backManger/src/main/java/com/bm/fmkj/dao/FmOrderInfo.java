package com.bm.fmkj.dao;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "fm_order_info")
public class FmOrderInfo {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 商品ID
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 订单类型1、买入; 2、卖出
     */
    @Column(name = "order_type")
    private Byte orderType;

    /**
     * 下单用户
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 卖家用户
     */
    @Column(name = "seller_id")
    private Integer sellerId;

    /**
     * 买家支付总金额
     */
    private BigDecimal payment;

    /**
     * 交易数量,购买或卖出数量
     */
    @Column(name = "trade_num")
    private Double tradeNum;

    /**
     * 买家支付方式1、微信; 2、支付宝;
     */
    @Column(name = "payment_type")
    private Byte paymentType;

    /**
     * 订单状态0、未付款; 1、已付款或已付P; 2、订单取消; 3、交易成功; -1、删除订单
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 交易完成时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 下单时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 订单修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 下单留言
     */
    @Column(name = "leave_msg")
    private String leaveMsg;

    /**
     * 付款确认0、未付款(金额);1、确认已付款(金额)
     */
    @Column(name = "is_pay")
    private Byte isPay;

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
     * 获取商品ID
     *
     * @return product_id - 商品ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置商品ID
     *
     * @param productId 商品ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取订单类型1、买入; 2、卖出
     *
     * @return order_type - 订单类型1、买入; 2、卖出
     */
    public Byte getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型1、买入; 2、卖出
     *
     * @param orderType 订单类型1、买入; 2、卖出
     */
    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取下单用户
     *
     * @return user_id - 下单用户
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置下单用户
     *
     * @param userId 下单用户
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取卖家用户
     *
     * @return seller_id - 卖家用户
     */
    public Integer getSellerId() {
        return sellerId;
    }

    /**
     * 设置卖家用户
     *
     * @param sellerId 卖家用户
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取买家支付总金额
     *
     * @return payment - 买家支付总金额
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * 设置买家支付总金额
     *
     * @param payment 买家支付总金额
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    /**
     * 获取交易数量,购买或卖出数量
     *
     * @return trade_num - 交易数量,购买或卖出数量
     */
    public Double getTradeNum() {
        return tradeNum;
    }

    /**
     * 设置交易数量,购买或卖出数量
     *
     * @param tradeNum 交易数量,购买或卖出数量
     */
    public void setTradeNum(Double tradeNum) {
        this.tradeNum = tradeNum;
    }

    /**
     * 获取买家支付方式1、微信; 2、支付宝;
     *
     * @return payment_type - 买家支付方式1、微信; 2、支付宝;
     */
    public Byte getPaymentType() {
        return paymentType;
    }

    /**
     * 设置买家支付方式1、微信; 2、支付宝;
     *
     * @param paymentType 买家支付方式1、微信; 2、支付宝;
     */
    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 获取订单状态0、未付款; 1、已付款或已付P; 2、订单取消; 3、交易成功; -1、删除订单
     *
     * @return order_status - 订单状态0、未付款; 1、已付款或已付P; 2、订单取消; 3、交易成功; -1、删除订单
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态0、未付款; 1、已付款或已付P; 2、订单取消; 3、交易成功; -1、删除订单
     *
     * @param orderStatus 订单状态0、未付款; 1、已付款或已付P; 2、订单取消; 3、交易成功; -1、删除订单
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取支付时间
     *
     * @return payment_time - 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置支付时间
     *
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取交易完成时间
     *
     * @return end_time - 交易完成时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置交易完成时间
     *
     * @param endTime 交易完成时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取下单时间
     *
     * @return create_time - 下单时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置下单时间
     *
     * @param createTime 下单时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取订单修改时间
     *
     * @return update_time - 订单修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置订单修改时间
     *
     * @param updateTime 订单修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取下单留言
     *
     * @return leave_msg - 下单留言
     */
    public String getLeaveMsg() {
        return leaveMsg;
    }

    /**
     * 设置下单留言
     *
     * @param leaveMsg 下单留言
     */
    public void setLeaveMsg(String leaveMsg) {
        this.leaveMsg = leaveMsg;
    }

    /**
     * 获取付款确认0、未付款(金额);1、确认已付款(金额)
     *
     * @return is_pay - 付款确认0、未付款(金额);1、确认已付款(金额)
     */
    public Byte getIsPay() {
        return isPay;
    }

    /**
     * 设置付款确认0、未付款(金额);1、确认已付款(金额)
     *
     * @param isPay 付款确认0、未付款(金额);1、确认已付款(金额)
     */
    public void setIsPay(Byte isPay) {
        this.isPay = isPay;
    }
}