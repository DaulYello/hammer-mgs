package com.bm.fmkj.service;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class FmRpoolService {
	@Autowired
	private FmRpoolMapper fmrpoolMapper;

	@Autowired
	private FmRecyleLogMapper fmRecyleLogMapper;

	@Autowired
	private FmIntegralInfoMapper fmintegralinfoMapper;

	public FmRpoolMapper getFmRpoolMapper() {
		return fmrpoolMapper;
	}

	public Pagenation<FmRpool> queryIntegrals(PageQuery pageQuery){
		try {
			PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
			List<FmRpool> fmRpools = fmrpoolMapper.selectAll();
			return PageUtil.toPagedResult(fmRpools);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 新增
	 * @param fmRpool
	 */
	public int insertFmRpool(FmRpool fmRpool) {
		try{
			fmRpool.setCreatedate(new Date());
			return fmrpoolMapper.insert(fmRpool);
		}catch (Exception e){
			e.getMessage();
			return 0;
		}
	}
	/**
	 * 修改
	 * @param fmRpool
	 */
	public int editIntegral(FmRpool fmRpool) {
		try{
			fmRpool.setUpdatedate(new Date());
			if(StringUtils.isNull(fmRpool.getRecycleNum())){
				fmRpool.setRecycleNum(0.0);
			}
			return fmrpoolMapper.updateByPrimaryKeySelective(fmRpool);
		}catch (Exception e){
			e.getMessage();
			return 0;
		}
	}
	/**
	 * 删除
	 * @param fmRpool
	 */
	public void dropIntegration(String id) {
		try{
			FmRpool fmRpool = new FmRpool();
			fmRpool.setId(Integer.parseInt(id));
			fmrpoolMapper.delete(fmRpool);
		}catch (Exception e){
			e.getMessage();
		}
	}

	public FmRpool queryRpoolByYear(Integer sysYear) {
		FmRpool fmRpool = new FmRpool();
		fmRpool.setYear(sysYear);
		return fmrpoolMapper.selectOne(fmRpool);
	}


	public void updateRecycle(FmRpool fmRpool, Double allotR, int uid) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("recyleNum", allotR);
		param.put("recyleType", RecyleEnum.TYPE_R.status);
		param.put("takeType", TakeEnum.TYPE_ALLOT.status);
		param.put("dateTime", new Date());
		param.put("uid", uid);
		param.put("friendId", uid);
		param.put("takeMsg", "系统回收了" + allotR + "R积分到积分池");
		fmrpoolMapper.updateByPrimaryKey(fmRpool);
		fmRecyleLogMapper.addRecyletLog(param);
	}

	public void recyleR(int uid, Double totalNum, FmRpool fmRpool, List<FmRecyleLog> recyleLogs) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("uid", uid);
		param.put("totalNum", totalNum);
		fmrpoolMapper.updateByPrimaryKeySelective(fmRpool);
		fmRecyleLogMapper.batchAddRecyleLog(recyleLogs);
		fmintegralinfoMapper.updateFmIntegral();
		fmintegralinfoMapper.recyleRToAccount(param);
	}
}