package com.bm.fmkj.controller;

import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.dao.PmImage;
import com.bm.fmkj.service.PmImageService;
import com.bm.fmkj.utils.PropUtil;
import com.bm.fmkj.utils.UploadImageUtil;
import com.xl.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/fmkj/image")
public class PmImageController extends BaseController {
	@Autowired
	private PmImageService pmimageService;

	@RequestMapping(value="upload",method= RequestMethod.POST)
	@ResponseBody
	public BaseResult upload(MultipartFile file){
		try {
			String strategyImagePath = PropUtil.getString("strategyImagePath");
			String strategyImageIpPath = PropUtil.getString("strategyImageIpPath");
			String fileName = UploadImageUtil.uploadImage(file, strategyImagePath);
			PmImage image = new PmImage();
			image.setImageUrl(strategyImageIpPath + fileName);
			image.setCreateDate(new Date());
			image.setPath(strategyImagePath);
			int row = pmimageService.insert(image);
			if(row > 0){
				return new BaseResult(BaseResultEnum.SUCCESS.status, "图片上传成功!", image.getId());
			}else{
				return new BaseResult(BaseResultEnum.ERROR.status, "图片上传失败!", image.getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new BaseResult(BaseResultEnum.ERROR.status, "图片上传失败!", false);
	}

}