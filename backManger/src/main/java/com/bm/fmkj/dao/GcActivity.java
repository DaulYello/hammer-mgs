package com.bm.fmkj.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "gc_activity")
public class GcActivity {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 竟锤发起人的id
     */
    private Integer startid;

    /**
     * 活动结束，中奖用户的id
     */
    private Integer getid;

    /**
     * 对应gc_ticket表中的主键
     */
    private Integer ticketid;

    /**
     * 对应活动类型表gc_activitytype中的主键
     */
    private Integer typeid;

    /**
     * 合约地址
     */
    private String contract;

    /**
     * 保证金
     */
    private Double bond;

    /**
     * 发起人的地点
     */
    private String place;

    /**
     * 产品的名称
     */
    private String pname;

    /**
     * 产品的数量
     */
    private Integer pnumber;

    /**
     * 产品价格
     */
    private Double price;

    /**
     * 产品的溢价
     */
    private Double premium;

    /**
     * 活动(竟锤)的状态 0:未开始  1:线上 2:下线
     */
    private Integer status;

    /**
     * 发货的标志0：没发货 1：已发货
     */
    private Integer delivergoodstatus;

    /**
     * 收货的标志 0：没收货 1：已收货
     */
    private Integer collectgoodstatus;

    /**
     * 是否通过审核 0：未审核 1：已经通过 2未通过
     */
    private Integer ispass;

    /**
     * 发起活动（竟锤）的时间
     */
    private Date time;

    /**
     * 开始时间
     */
    private Date begintime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 参与活动最小人数
     */
    private Integer minnum;

    /**
     * 票面值
     */
    private Integer par;

    /**
     * 产品的描述详情
     */
    private String pdescribe;

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
     * 获取竟锤发起人的id
     *
     * @return startid - 竟锤发起人的id
     */
    public Integer getStartid() {
        return startid;
    }

    /**
     * 设置竟锤发起人的id
     *
     * @param startid 竟锤发起人的id
     */
    public void setStartid(Integer startid) {
        this.startid = startid;
    }

    /**
     * 获取活动结束，中奖用户的id
     *
     * @return getid - 活动结束，中奖用户的id
     */
    public Integer getGetid() {
        return getid;
    }

    /**
     * 设置活动结束，中奖用户的id
     *
     * @param getid 活动结束，中奖用户的id
     */
    public void setGetid(Integer getid) {
        this.getid = getid;
    }

    /**
     * 获取对应gc_ticket表中的主键
     *
     * @return ticketid - 对应gc_ticket表中的主键
     */
    public Integer getTicketid() {
        return ticketid;
    }

    /**
     * 设置对应gc_ticket表中的主键
     *
     * @param ticketid 对应gc_ticket表中的主键
     */
    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    /**
     * 获取对应活动类型表gc_activitytype中的主键
     *
     * @return typeid - 对应活动类型表gc_activitytype中的主键
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * 设置对应活动类型表gc_activitytype中的主键
     *
     * @param typeid 对应活动类型表gc_activitytype中的主键
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 获取合约地址
     *
     * @return contract - 合约地址
     */
    public String getContract() {
        return contract;
    }

    /**
     * 设置合约地址
     *
     * @param contract 合约地址
     */
    public void setContract(String contract) {
        this.contract = contract;
    }

    /**
     * 获取保证金
     *
     * @return bond - 保证金
     */
    public Double getBond() {
        return bond;
    }

    /**
     * 设置保证金
     *
     * @param bond 保证金
     */
    public void setBond(Double bond) {
        this.bond = bond;
    }

    /**
     * 获取发起人的地点
     *
     * @return place - 发起人的地点
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置发起人的地点
     *
     * @param place 发起人的地点
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * 获取产品的名称
     *
     * @return pname - 产品的名称
     */
    public String getPname() {
        return pname;
    }

    /**
     * 设置产品的名称
     *
     * @param pname 产品的名称
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * 获取产品的数量
     *
     * @return pnumber - 产品的数量
     */
    public Integer getPnumber() {
        return pnumber;
    }

    /**
     * 设置产品的数量
     *
     * @param pnumber 产品的数量
     */
    public void setPnumber(Integer pnumber) {
        this.pnumber = pnumber;
    }

    /**
     * 获取产品价格
     *
     * @return price - 产品价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置产品价格
     *
     * @param price 产品价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取产品的溢价
     *
     * @return premium - 产品的溢价
     */
    public Double getPremium() {
        return premium;
    }

    /**
     * 设置产品的溢价
     *
     * @param premium 产品的溢价
     */
    public void setPremium(Double premium) {
        this.premium = premium;
    }

    /**
     * 获取活动(竟锤)的状态 0:未开始  1:线上 2:下线
     *
     * @return status - 活动(竟锤)的状态 0:未开始  1:线上 2:下线
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置活动(竟锤)的状态 0:未开始  1:线上 2:下线
     *
     * @param status 活动(竟锤)的状态 0:未开始 1:线上（participate） 2:下线（对应合约cancel，end） 3.审核不通过
     * 4.活动通过进入待参与状态,5.活动进入close，6.notice,7.竟锤成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取发货的标志0：没发货 1：已发货
     *
     * @return delivergoodstatus - 发货的标志0：没发货 1：已发货
     */
    public Integer getDelivergoodstatus() {
        return delivergoodstatus;
    }

    /**
     * 设置发货的标志0：没发货 1：已发货
     *
     * @param delivergoodstatus 发货的标志0：没发货 1：已发货
     */
    public void setDelivergoodstatus(Integer delivergoodstatus) {
        this.delivergoodstatus = delivergoodstatus;
    }

    /**
     * 获取收货的标志 0：没收货 1：已收货
     *
     * @return collectgoodstatus - 收货的标志 0：没收货 1：已收货
     */
    public Integer getCollectgoodstatus() {
        return collectgoodstatus;
    }

    /**
     * 设置收货的标志 0：没收货 1：已收货
     *
     * @param collectgoodstatus 收货的标志 0：没收货 1：已收货
     */
    public void setCollectgoodstatus(Integer collectgoodstatus) {
        this.collectgoodstatus = collectgoodstatus;
    }

    /**
     * 获取是否通过审核 0：未审核 1：已经通过 2未通过
     *
     * @return ispass - 是否通过审核 0：未审核 1：已经通过 2未通过
     */
    public Integer getIspass() {
        return ispass;
    }

    /**
     * 设置是否通过审核 0：未审核 1：已经通过 2未通过
     *
     * @param ispass 是否通过审核 0：未审核 1：已经通过 2未通过
     */
    public void setIspass(Integer ispass) {
        this.ispass = ispass;
    }

    /**
     * 获取发起活动（竟锤）的时间
     *
     * @return time - 发起活动（竟锤）的时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置发起活动（竟锤）的时间
     *
     * @param time 发起活动（竟锤）的时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取开始时间
     *
     * @return begintime - 开始时间
     */
    public Date getBegintime() {
        return begintime;
    }

    /**
     * 设置开始时间
     *
     * @param begintime 开始时间
     */
    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    /**
     * 获取结束时间
     *
     * @return endtime - 结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置结束时间
     *
     * @param endtime 结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 获取活动名称
     *
     * @return name - 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置活动名称
     *
     * @param name 活动名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取参与活动最小人数
     *
     * @return minnum - 参与活动最小人数
     */
    public Integer getMinnum() {
        return minnum;
    }

    /**
     * 设置参与活动最小人数
     *
     * @param minnum 参与活动最小人数
     */
    public void setMinnum(Integer minnum) {
        this.minnum = minnum;
    }

    /**
     * 获取票面值
     *
     * @return par - 票面值
     */
    public Integer getPar() {
        return par;
    }

    /**
     * 设置票面值
     *
     * @param par 票面值
     */
    public void setPar(Integer par) {
        this.par = par;
    }

    /**
     * 获取产品的描述详情
     *
     * @return pdescribe - 产品的描述详情
     */
    public String getPdescribe() {
        return pdescribe;
    }

    /**
     * 设置产品的描述详情
     *
     * @param pdescribe 产品的描述详情
     */
    public void setPdescribe(String pdescribe) {
        this.pdescribe = pdescribe;
    }
}