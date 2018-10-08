package com.bm.fmkj.utils;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class JDWXUtil {
	
	/**
	 * 身份認證檢測
	 * @param ha
	 * @return
	 */
	public static synchronized Boolean cardRealName(String cardNum,String name,HashMap<String, String> map){
		RequestModel model = new RequestModel();
		model.setGwUrl(GlobalConstants.getInterfaceUrl("cardPath"));
		model.setAppkey(GlobalConstants.getInterfaceUrl("cardPwd"));
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("cardNo", cardNum); // 访问参数
		queryMap.put("realName", name); // 访问参数
		model.setQueryParams(queryMap);
		WxApiCall call = new WxApiCall();
		call.setModel(model);
		String request = call.request();

		JSONObject jb = JSONObject.parseObject(request);

		Object code = jb.get("code");
		if (!"10000".equals(code)) {
			map.put("code", "500");
			map.put("message", "查询失败，请检查网络是否正常");
			return false;
		}
		JSONObject result = (JSONObject) jb.get("result");

		JSONObject resp = (JSONObject) result.get("result");

		Boolean isok = (Boolean) resp.get("isok");
		if (!isok) {
			map.put("code", "300");
			map.put("message", "查询成功，身份证号与姓名匹配失败");
			return false;
		}
		return true;
	}
	
	/**
	 * 敏感词汇检测
	 */
	public static synchronized String wordIsOk(String word) {
		RequestModel model = new RequestModel();
		model.setGwUrl(GlobalConstants.getInterfaceUrl("wordPath"));
		model.setAppkey(GlobalConstants.getInterfaceUrl("cardPwd"));
//		model.setGwUrl("https://way.jd.com/tongchengyuan/minganci");
//		model.setAppkey("8fda758a86044ca3a6b67f268c3964a6");
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("src",word); //访问参数
		model.setQueryParams(queryMap);
		WxApiCall call = new WxApiCall();
		call.setModel(model);
		String request = call.request();
		JSONObject jb = JSONObject.parseObject(request);

		Object code = jb.get("code");
		if (!"10000".equals(code)) {
			return "500";
		}
		JSONObject result = (JSONObject) jb.get("result");
		String result1 =  (String) result.get("result");
		return result1;
	}
	
	public static void main(String[] args) {
		String word = "俺是个带回归";
		String wordIsOk = wordIsOk(word);
		System.out.println(wordIsOk);
		if(!word.equals(wordIsOk)) {
			System.out.println("含有敏感词汇");
		}
		
	}
}
