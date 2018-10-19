package com.bm.fmkj.constant;


/**
 * @Description:   收取类型:0、用户收取;1、定时任务回收;2、释放CNT时没有用户回收

 * @Author: youxun
 * @Version: 1.0
 **/
public enum TakeEnum {

    USER_LOST(-1,"用户扣除"),

    USER_GET(0,"用户获得"),

    TYPE_TASK(1,"定时任务回收"),

    TYPE_ALLOT(2, "释放时没有用户的情况回收");

    public int status;

    public String msg;


    private TakeEnum(int status, String msg){
        this.status = status;
        this.msg = msg;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
