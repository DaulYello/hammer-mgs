package com.bm.fmkj.utils;

import java.util.ResourceBundle;

public class PropUtil {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sysconfig");
	
	public static String getString(String key) {
		
		if(RESOURCE_BUNDLE == null) {
			return "";
		}
		return RESOURCE_BUNDLE.getString(key);
	}
}
