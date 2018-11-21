package com.bm.fmkj.controller;

import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.PmImage;
import com.bm.fmkj.service.PmImageService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/fmkj/PmImage")
public class PmImageController extends BaseController {
	@Autowired
	private PmImageService pmimageService;

	private Logger LOGGER= LoggerFactory.getLogger(PmImageController.class);

	@PostMapping("/uploadTaskImage")
	public BaseResult uploadTaskImage(@RequestParam("file") MultipartFile file) {
		try{
			LOGGER.info("dljkgpdrf;lgdf[pgkfd;pgk");
			LOGGER.info("dljkgpdrf;lgdf[pgkfd;pgk");
			LOGGER.info("dljkgpdrf;lgdf[pgkfd;pgk");
			LOGGER.info("dljkgpdrf;lgdf[pgkfd;pgk");
			LOGGER.info("dljkgpdrf;lgdf[pgkfd;pgk");
			LOGGER.info("dljkgpdrf;lgdf[pgkfd;pgk");
			LOGGER.info("dljkgpdrf;lgdf[pgkfd;pgk");
			/*if(StringUtils.isNull(uid)){
				return  new BaseResult(BaseResultEnum.BLANK.status, "用户ID不能为空", false);
			}
			if(StringUtils.isNull(status)){
				return  new BaseResult(BaseResultEnum.BLANK.status, "status不能为空", false);
			}*/
			/*HcUserimage userimage = new HcUserimage();
			userimage.setUid(uid);
			userimage.setUrl(userCodeImagePath);
			String newFileName=PropertiesUtil.uploadImage(file,userCodeImagePath);
			if(status == ImageEnum.TYPE_FULL.status){
				userimage.setFullPhoto(userCodeImageIpPath+newFileName);
			}else if(status == ImageEnum.TYPE_REVERSE.status){
				userimage.setReversePhoto(userCodeImageIpPath+newFileName);
			}
			userimage.setTime(new Date());
			HcUserimage hm = new HcUserimage();
			hm.setUid(uid);
			EntityWrapper<HcUserimage> wrapper = new EntityWrapper<>(hm);
			HcUserimage hcUserimage = hcUserimageService.selectOne(wrapper);
			boolean result = false;
			if(StringUtils.isNull(hcUserimage)){
				result = hcUserimageService.insert(userimage);
			}else{
				userimage.setId(hcUserimage.getId());
				result = hcUserimageService.updateById(userimage);
			}*/
			/*if(result){
				return new BaseResult(BaseResultEnum.SUCCESS.getStatus(), "上传成功!", true);
			}else {
				return new BaseResult(BaseResultEnum.SUCCESS.getStatus(), "上传失败!", false);
			}*/
			return new BaseResult(BaseResultEnum.ERROR.getStatus(), "上传失败!", "");
		}catch (Exception e){
			return new BaseResult(BaseResultEnum.ERROR.getStatus(), "上传失败!", e.getMessage());
		}
	}
}