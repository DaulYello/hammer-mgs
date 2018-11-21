package com.bm.fmkj.utils;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageUtil {
	/**
	 * @author yangshengbin
	 * @Description：生成文件路径
	 * @date 2018/8/30 0030 11:17
	 * @param
	 * @return
	 */
	public static String uploadImage(MultipartFile file, String url) throws IOException {
		if (file != null && url != null) {
			new File(url).mkdirs();//创建文件夹(已存在则无效)
			UUID uuid = UUID.randomUUID();
			// 获取上传的文件的名称
			String filename = file.getOriginalFilename();
			//获取后缀
			String prefix=filename.substring(filename.lastIndexOf(".")+1);

			String originalFilename =uuid.toString().replaceAll("-", "");
			Long time = new Date().getTime();//获取当前时间为标识
			String newFileName = time.toString()+originalFilename+ "." + prefix;//新的文件名
			//String newFileName = time.toString()+originalFilename;//新的文件名
			File targetFile = null; //封装上传文件位置的全路径
			try {
				targetFile = new File(url,newFileName);
			} catch (Exception e) {
				throw new RuntimeException("保存图片异常：" + e.getMessage());
			}
			file.transferTo(targetFile);//把本地文件上传到 封装上传文件位置的全路径 下
			return newFileName;//返回新的文件路径名+文件名
		}

		return null;
	}
}