/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.bm.fmkj.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {
	
	 public static void main(String[] args) throws IOException { 
		 Properties p=new Properties(); 
		 InputStream is=ClassLoader.getSystemResourceAsStream("dataSource.properties"); 
//		  InputStream is=SourceLoader.class.getResourceAsStream("dataSource.properties");
		  p.load(is); System.out.println(p);
		  System.out.println(p.getProperty("jdbc.url")); 
	 }
}