package com.xl.utils;
//package com.xl.utils;
//
//import java.util.Date;
//
//import com.xl.fmkj.controller.HcLeavemsgController;
//import com.xl.fmkj.dao.HcLeavemsg;
//import com.xl.fmkj.service.HcLeavemsgService;
//
//public class TestDatebase {
//	private static HcLeavemsgController hcLeavemsgController = new HcLeavemsgController();
//	private static HcLeavemsgService hcLeavemsgService = new HcLeavemsgService();
//	private static HcLeavemsg hcLeavemsg = new HcLeavemsg();
//
//	public static void main(String[] args) {
//		System.out.println("HcLeavemsgService=="+hcLeavemsgService);
//		int i = 0;
//		while (true) {
//			System.out.println(DateUtil.dateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
//			i++;
//			if (i == 100) {
//				return;
//			}
//			;
//			hcLeavemsg.setMyid(1);
//			hcLeavemsg.setFid(2);
//			hcLeavemsg.setMsg("你好吗！");
//			hcLeavemsg.setTime(DateUtil.getNowInMillis(0L));
//			hcLeavemsgService.leaveMsg(hcLeavemsg);
//		}
//	}
//}