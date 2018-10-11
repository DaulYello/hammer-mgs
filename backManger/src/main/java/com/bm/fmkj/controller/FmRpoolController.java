package com.bm.fmkj.controller;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.BaseResult;
import com.bm.fmkj.base.BaseResultEnum;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xl.utils.BaseController;
import com.bm.fmkj.dao.FmRpool;
import com.bm.fmkj.service.FmRpoolService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/fmkj/FmRpool")
public class FmRpoolController extends BaseController {
	@Autowired
	private FmRpoolService fmrpoolService;

	private Logger log= LoggerFactory.getLogger(GcActivityController.class);
	/**
	 * 活动分页查询 status
	 */
	@RequestMapping(value = "queryIntegral", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult<Pagenation<FmRpool>> queryIntegral(@RequestParam HashMap<String,Object> params) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			log.info("queryIntegral-params={}", JSON.toJSONString(params));
			PageQuery pageQuery = new PageQuery();
			int pageNo = Integer.parseInt((String)params.get("pageNo"));
			int pageSize = Integer.parseInt((String)params.get("pageSize"));
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			pageQuery.setParam(params);
			Pagenation<FmRpool> pageResult = fmrpoolService.queryIntegrals(pageQuery);
			return new BaseResult<Pagenation<FmRpool>>(BaseResultEnum.SUCCESS,pageResult);
		} catch (Exception e) {
			throw new RuntimeException("查询活动失败：");
		}
	}

    /**
     * 新增
     */
    @RequestMapping(value="addIntegrate",method=RequestMethod.POST)
    @ResponseBody
    public BaseResult<Boolean> addIntegrate(FmRpool fmRpool){
        log.info("addIntegrate-params={}", JSON.toJSONString(fmRpool));
        if(StringUtils.isNull(fmRpool)) {
            return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
        }
        int result = fmrpoolService.insertFmRpool(fmRpool);
        if(result > 0) {
            return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
        }else {
            return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);
        }
    }


    /**
     * 编辑修改
     */
    @RequestMapping(value="editIntegral",method=RequestMethod.POST)
    @ResponseBody
    public BaseResult<Boolean> editIntegral(FmRpool fmRpool){
        log.info("editIntegral-params={}", JSON.toJSONString(fmRpool));
        if(StringUtils.isNull(fmRpool)|| fmRpool.getId() == null) {
            return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
        }
        int result = fmrpoolService.editIntegral(fmRpool);
        if(result > 0) {
            return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
        }
        return new BaseResult<Boolean>(BaseResultEnum.ERROR,false);

    }


    /**
     * 删除
     */
    @RequestMapping(value="dropIntegration",method=RequestMethod.GET)
    @ResponseBody
    public BaseResult<Boolean> dropIntegration(@RequestParam String id){
        log.info("dropIntegration-params={}", JSON.toJSONString(id));
        if(id == null) {
            return new BaseResult<Boolean>(BaseResultEnum.BLANK,false);
        }
        try {
            fmrpoolService.dropIntegration(id);
            return new BaseResult<Boolean>(BaseResultEnum.SUCCESS,true);
        } catch (Exception e) {
            return new BaseResult<Boolean>(BaseResultEnum.ERROR.getStatus(), "删除异常:" + e.getMessage(), false);
        }
    }
}