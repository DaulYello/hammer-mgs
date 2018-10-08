package com.bm.fmkj.utils;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendSMS {
	private static String Url = "https://way.jd.com/chuangxin/dxjk?";
	
	private static Integer mobile_code;
	
	public static String sendsms(String phone) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(Url);
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

		mobile_code =  (int) ((Math.random() * 9 + 1) * 100000);
		
		String content = new String("【锤多宝】您的验证码："+mobile_code+"，请注意保密，如非本人操作请尽快联系客服！");
		NameValuePair[] data = { // 提交短信
				new NameValuePair("mobile", phone), 
				new NameValuePair("content", content), 
				new NameValuePair("appkey", "8fda758a86044ca3a6b67f268c3964a6")
				};
		method.setRequestBody(data);
		try {
			client.executeMethod(method);
			String SubmitResult = method.getResponseBodyAsString();
			System.out.println(SubmitResult);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "发送成功";
	}
	
	public static String getMobile_code() {
		return mobile_code.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new Date());
		sendsms("17385834035");
		System.out.println(new Date());
	}
}
