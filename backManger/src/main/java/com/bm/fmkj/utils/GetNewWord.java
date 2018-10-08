package com.bm.fmkj.utils;


/**
 * 读取敏感词库，获取敏感词汇
 * @author hsy
 *
 */
public class GetNewWord {
	private static SensitiveWord sw;
	
    public static String wordIsOk(String word) {
    	long startNumer = System.currentTimeMillis();
    	if(sw==null) {
    		sw = new SensitiveWord("CensorWords.txt"); 
    	}
	    sw.InitializationWork();  
	    System.out.println("被检测字符串长度:"+word.length());  
	    String result = sw.filterInfo(word);
	    long endNumber = System.currentTimeMillis();  
	    System.out.println("总共耗时:"+(endNumber-startNumer)+"ms");  
	    System.out.println("替换后的字符串为:\n"+result);
		return result;
    }
    
    public static void main(String[] args) {

	}
    
    
}
