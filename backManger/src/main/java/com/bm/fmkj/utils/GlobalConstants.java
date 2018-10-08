package com.bm.fmkj.utils;

import java.util.Properties;

public class GlobalConstants {

    public static Properties interfaceUrlProperties;

/**
 * 
 * @Description: TODO
 * @param @param key
 * @param @return   
 * @author dapengniao
 */
    public static String getInterfaceUrl(String key) {
    	
        return (String) interfaceUrlProperties.get(key);
        /**
    	 * 测试用，上线放开！
    	 */
//        return "1"; 
    }

}
