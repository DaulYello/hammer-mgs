package com.fmkj.systemanager;

import java.io.UnsupportedEncodingException;



import com.bm.fmkj.utils.PropUtil;


/**
 * 单元测试
 * @author huangshuang
 *
 */
public class SysteManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*double data=Math.random();
		
		int data2=8;
		System.out.println("------>:"+Math.floor(data*100));
		System.out.println("------>:"+Math.ceil(data*100));*/
		/*double result = data / data2;
		System.out.println("------>:"+result);
		System.out.println("------>:"+Math.ceil(result));
		System.out.println("------>:"+((int)Math.ceil(result)));*/
		
		/*try {
			StringBuilder sb = new StringBuilder();
			
			String keyValue = new String(PropUtil.getString("head").getBytes("ISO-8859-1"), "UTF-8");
			String username=new String(PropUtil.getString("message").getBytes("ISO-8859-1"),"UTF-8");
			sb.append(keyValue);
			sb.append("----"+username);
			//util.StringHanlder.transformCodeIso8859Style(PropUtil.getString("head"),"utf-8");
			//PropUtil.getString("head").getBytes("ISO");
			System.out.println("------>:"+sb.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String string = "1";
		
		String lString []= string.split(","); 
		for(int i=0;i<lString.length;i++) {
			System.out.println(lString[i]);
		}
		
		
	}
}
