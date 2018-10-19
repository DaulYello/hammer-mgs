package com.bm.fmkj.constant;

/**
 * @Description:
 * 签到       1飞羽/次
 * 参与活动	  2飞羽/次
 * 发布活动	 10飞羽/次
 * 购买CNT	 2飞羽/次
 * 邀请人注册	 5飞羽/次
 * 上传头像	 5飞羽
 * 绑定邮箱	 10飞羽
 * 自己注册	 10飞羽
 * 绑定支付宝	  15飞羽
 * 绑定微信支付	15飞羽
 * 实名认证	 20飞羽
 * 完成任务	 1飞羽/次
 * 充值虚拟货币	5飞羽/次
 * 任务中心发布任务	10飞羽/次
 * 兑换飞羽	 1飞羽/1CNT
 * @Author: youxun
 * @Version: 1.0
 **/
public enum PointEnum {

    //实名认证
    REAL_AUTHENT(7,20D),
    //完成任务
    FINISH_TASK(12,1D),
    //充值虚拟货币
    CHARGE_VIRT_CURRENCY(13,5D),
    //任务中心发布任务
    PUBLISH_TASK(14,10D),
    //兑换飞羽
    EXCHANGE_POINT(15,1D);


    public int pointId;
    public Double pointNum;

    private PointEnum(int pointId, double pointNum) {
        this.pointId = pointId;
        this.pointNum = pointNum;
    }

    public int getPointId() {
        return pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public double getPointNum() {
        return pointNum;
    }

    public void setPointNum(double pointNum) {
        this.pointNum = pointNum;
    }
}
