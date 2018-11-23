package com.bm.fmkj.service;

import java.util.List;

import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.dao.FmRecyleLog;
import com.bm.fmkj.dao.FmRecyleLogMapper;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.HcAccount;
import com.bm.fmkj.dao.HcAccountMapper;
import com.bm.fmkj.model.PageModel;
import com.github.pagehelper.PageHelper;

@Transactional
@Service
public class HcAccountService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(HcAccountService.class);

	@Autowired
	private HcAccountMapper hcaccountMapper;

	@Autowired
	private FmRecyleLogMapper fmRecyleLogMapper;

	public HcAccountMapper getHcAccountMapper() {
		return hcaccountMapper;
	}
	
	/**
	 * 分页查询用户邀请人数排行榜
	 */
	public List<HcAccount> queryUserRank(PageModel<HcAccount> pm){
		return hcaccountMapper.queryUserRank(pm);
	}
	
	/**
	 * 通过id查询用户
	 */
	public HcAccount queryUserById(HcAccount ha) {
		return hcaccountMapper.selectOne(ha);
	}
	
	/**
	 *获取用户的证件照片进行审核验证
	 * @return
	 */
	public List<T> queryUserIdImage(PageModel<HcAccount> pm){
		return hcaccountMapper.queryUserIdImage(pm);
	}
	
	/**
	 * 分页获取用户的信息
	 * @param pm
	 * @return
	 */
	public Pagenation<HcAccount> queryUserInfo(PageQuery pageQuery) {
		try {
			PageHelper.startPage(pageQuery.getPageNo(),pageQuery.getPageSize());  
			List<HcAccount> hccounts = hcaccountMapper.queryUserInfo(pageQuery.getParam());
			return PageUtil.toPagedResult(hccounts);
		} catch (Exception e) {
			LOGGER.error("用户信息查询异常：" + e.getMessage());
			throw new RuntimeException();
		}
	}
	/**
	 * 获取黑名单用户
	 * @param pm
	 * @return
	 */
	public List<HcAccount> queryUserBlack(PageModel<HcAccount> pm){
		return hcaccountMapper.queryUserBlack(pm);
	}

    public List<T> queryUserPointNum() {
		return hcaccountMapper.queryUserPointNum();
    }

	public void updateCntById(Integer uid, int superUid, Double takeNum, List<FmRecyleLog> fmRecyleLogList) {

		HcAccount hc = hcaccountMapper.selectByPrimaryKey(uid);
		hc.setCnt(hc.getCnt() + takeNum);
		int update = hcaccountMapper.updateByPrimaryKeySelective(hc);
		if(update > 0 ){
			LOGGER.info("邀请周排行榜更新用户"+hc.getNickname()+"CNT成功");
		}else{
			LOGGER.info("邀请周排行榜更新用户"+hc.getNickname()+"CNT失败");
		}
		HcAccount superHc = hcaccountMapper.selectByPrimaryKey(superUid);
		superHc.setCnt(superHc.getCnt() + takeNum);
		int updateSuper = hcaccountMapper.updateByPrimaryKeySelective(superHc);
		if(update > 0 ){
			LOGGER.info("邀请周排行榜更新公司账户"+hc.getNickname()+"CNT成功");
		}else{
			LOGGER.info("邀请周排行榜更新公司账户"+hc.getNickname()+"CNT失败");
		}
		fmRecyleLogMapper.batchAddRecyleLog(fmRecyleLogList);

	}
}