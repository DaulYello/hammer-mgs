package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.PmPart;
import com.bm.fmkj.domain.PmPartDto;
import com.bm.fmkj.service.PmPartService;
import com.bm.fmkj.utils.StringUtils;
import com.xl.utils.BaseController;
import com.xl.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/fmkj/part")
public class PmPartController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PmPartController.class);

	@Autowired
	private PmPartService pmpartService;

	/**
	 * 列表
	 */
	@RequestMapping(value="getPartPage",method= RequestMethod.GET)
	@ResponseBody
	public BaseResult getPartPage(@RequestParam HashMap<String, Object> params){
		LOGGER.info("getPartPage-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation<PmPartDto> pagedResult = pmpartService.getPartPage(pageQuery);
		return new BaseResult(BaseResultEnum.SUCCESS,pagedResult);
	}

	@RequestMapping(value="exportPart",method= RequestMethod.GET)
	@ResponseBody
	public BaseResult exportPart(@RequestParam HashMap<String, Object> params){
		LOGGER.info("exportPart-params={}", JSON.toJSONString(params));
		if(StringUtils.isNull(params.get("statusValue"))){
			params.put("statusValue", 1);
		}
		List<PmPartDto> downLoadList = pmpartService.downLoadList(params);

		if(StringUtils.isEmpty(downLoadList)){
			return new BaseResult(BaseResultEnum.ERROR.status,"没有可导出的数据", false);
		}
		File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		String exportPath = desktopPath + "\\" + DateUtil.dateStr(new Date(),"yyyy-MM-dd") + "导出数据";
		File mkfile =new File(exportPath);
		if(!mkfile.exists()){//如果文件夹不存在
			mkfile.mkdir();//创建文件夹
		}
		for(PmPartDto pm : downLoadList){
			try{
				String imageUrl = pm.getImageUrl();
				String imageArr [] = imageUrl.split(",");
				//导出提交数据
				String path = exportPath + "\\" + pm.getTitle() + "-【" + pm.getId() + "】" + pm.getNickName() + "-提交数据";
				exportSumitData(path, pm);
				//导出图片
				for(int i = 0; i < imageArr.length; i++){
					String image = imageArr[i];
					String prefix = image.substring(image.lastIndexOf(".")+1);
					int index = i + 1;
					downloadPicture(image, path + "\\" + "审核图片-" + index + "." + prefix);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return new BaseResult(BaseResultEnum.SUCCESS,true);
	}


	//链接url下载图片
	private void downloadPicture(String imageUrl,String path) {
		URL url = null;
		try {
			url = new URL(imageUrl);
			DataInputStream dataInputStream = new DataInputStream(url.openStream());

			FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length;

			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void exportSumitData(String path, PmPartDto pm) throws IOException {
		List extendList = pmpartService.getPartExtendList(pm.getId());
		if(StringUtils.isEmpty(extendList)){
			return;
		}
		File file=new File(path);
		if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
		BufferedWriter bw = new BufferedWriter(new FileWriter(path + "\\提交信息.txt"));
		for(int i = 0; i < extendList.size(); i++){
            HashMap extendMap = (HashMap) extendList.get(i);
            String clounmName = (String) extendMap.get("clounmName");
            String xValue = (String) extendMap.get("xvalue");
            bw.write(clounmName + ":" + xValue);
            bw.newLine();
        }
		bw.close();//一定要关闭文件
	}

	/**
	 * 列表
	 */
	@RequestMapping(value="getPartExtendPage",method= RequestMethod.GET)
	@ResponseBody
	public BaseResult getPartExtendPage(@RequestParam HashMap<String, Object> params){
		LOGGER.info("getPartExtendPage-params={}", JSON.toJSONString(params));
		PageQuery pageQuery = new PageQuery();
		int pageNo = Integer.parseInt((String)params.get("pageNo"));
		int pageSize = Integer.parseInt((String)params.get("pageSize"));
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		pageQuery.setParam(params);
		Pagenation pagedResult = pmpartService.getPartExtendPage(pageQuery);
		return new BaseResult(BaseResultEnum.SUCCESS,pagedResult);
	}


	/**
	 * 审核
	 */
	@RequestMapping(value="auditPart",method=RequestMethod.POST)
	@ResponseBody
	public BaseResult<Boolean> auditPart(PmPart pmPart){
		LOGGER.info("auditPart-params={}", JSON.toJSONString(pmPart));
		if(StringUtils.isNull(pmPart)) {
			return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
		}
		int result = pmpartService.auditPart(pmPart);
		if(result > 0) {
			return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
		}else {
			return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
		}
	}

}