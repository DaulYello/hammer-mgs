package com.bm.fmkj.utils;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageUtil {
	public static String uploadImage(MultipartFile file,String url) throws IOException{
		if (file != null && url != null) {
//			String filePath = url;//保存图片的路径
		       
	        new File(url).mkdirs();//创建文件夹(已存在则无效)
	        
	        String originalFilename = file.getOriginalFilename();//获取原始图片的拓展名
	        
	        Long time = new Date().getTime();//获取当前时间为标识
	        
	        String newFileName = time.toString()+originalFilename;//新的文件名
	        File targetFile = new File(url,newFileName); //封装上传文件位置的全路径
	        file.transferTo(targetFile);//把本地文件上传到 封装上传文件位置的全路径 下
	        
//	        String filePath2 = "image/";
	        return newFileName;//返回新的文件路径名+文件名
//	        return true;
		}
		return null;
	}
}