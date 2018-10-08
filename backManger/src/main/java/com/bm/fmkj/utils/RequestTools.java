package com.bm.fmkj.utils;

/**
 * Created by swh on 17-11-20.
 */
public class RequestTools {
	/**
	 * 判断字符串是否为空
	 * @param cs
	 * @return
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if(cs != null && (strLen = cs.length()) != 0) {
			for(int i = 0; i < strLen; ++i) {
				if(!Character.isWhitespace(cs.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	public static boolean isNotBlank(CharSequence cs){
		return !isBlank(cs);
	}
}
