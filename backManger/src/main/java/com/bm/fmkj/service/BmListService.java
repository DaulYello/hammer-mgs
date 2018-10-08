package com.bm.fmkj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.dao.BmList;
import com.bm.fmkj.dao.BmListMapper;
import com.bm.fmkj.dao.HcAccount;
import com.bm.fmkj.dao.HcAccountMapper;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Transactional
@Service
public class BmListService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(BmListService.class);

	@Autowired
	private BmListMapper bmlistMapper;
	
	@Autowired
	private HcAccountMapper hcAccountMapper;
	
	public BmListMapper getBmListMapper() {
		return bmlistMapper;
	}

	/**
	 * 名单用户列表查询
	 */
	public Pagenation<HcAccount> queryUserByExample(PageQuery pageQuery){
		try {
			PageHelper.startPage(pageQuery.getPageNo(),pageQuery.getPageSize());  
			List<HcAccount> hccounts = hcAccountMapper.queryUserByExample(pageQuery.getParam());
			return PageUtil.toPagedResult(hccounts);
		} catch (Exception e) {
			LOGGER.error("用户信息查询异常：" + e.getMessage());
			throw new RuntimeException();
		}
	}
	
	/**
	 * 通过电话号码或者竟锤id
	 * @param ha
	 * @return
	 */
	public HcAccount selectUserByExample(HcAccount ha) {
		return hcAccountMapper.selectUserByExample(ha);
	}
	
	/**
	 * 名单移除
	 */
	public int deleteBmListByUid(BmList bl) {
		return bmlistMapper.delete(bl);
	}
	
	/**
	 * 名单添加 
	 */
	public int insertBmListByExample(BmList bl) {
		Example example = new Example(BmList.class);
		Criteria criteria = example.createCriteria();
		criteria.andCondition("uid=", bl.getUid());
		List<BmList> list = bmlistMapper.selectByExample(example);
		if(list.size()>0) {
			return bmlistMapper.updateByExample(bl, example);
		}
		return bmlistMapper.insertSelective(bl);
	}

	/**
	 * 拉入黑名单
	 * @param bl
	 */
	public boolean drawBlackList(BmList bl) {
		try {
			Example example = new Example(BmList.class);
			Criteria criteria = example.createCriteria();
			criteria.andCondition("uid=",bl.getUid());
			bl.setLimitLogin(1);
			List<BmList> bmList = bmlistMapper.selectByExample(example);
			if(bmList.size()>0) {//如果已经有了，就更新limit_login
				return bmlistMapper.updateByExampleSelective(bl, example) > 0 ? true : false;
			}else {//如果在黑名单表中没有记录，就插入一条黑名单记录
				return bmlistMapper.insertSelective(bl) > 0 ? true : false;
			}
		} catch (Exception e) {
			throw new RuntimeException("拉入黑名单出现异常:" + e.getMessage());
		}
	}

	/**
	 * 将用户移除黑名单，这里只的是该用户可以登录APP了
	 * @param bl
	 * @return
	 */
	public boolean removeBlackList(BmList bl) {
		try {
			Example example = new Example(BmList.class);
			Criteria criteria = example.createCriteria();
			criteria.andCondition("uid=",bl.getUid());
			List<BmList> lists = bmlistMapper.selectByExample(example);
			if(lists.get(0).getStatus()!=2) {//目前这个黑名单表  status限制用户发起竟锤活动，limit_login限制用户登录APP
				return bmlistMapper.delete(bl) > 0 ? true : false;
			}else {
				bl.setLimitLogin(0);
				return bmlistMapper.updateByExampleSelective(bl, example) > 0 ? true : false;
			}
		} catch (Exception e) {
			throw new RuntimeException("恢复出现异常:" + e.getMessage());
		}
	}
	
	public boolean setWhite(String ids) {
		try {
			LOGGER.debug("设置白名单");
			String idStr []= ids.split(",");
			for(int i=0;i<idStr.length;i++) {
				System.out.println(idStr[i]);
			}
			return bmlistMapper.setWhite(idStr)>0?true:false;
		} catch (Exception e) {
			throw new RuntimeException("恢复出现异常:" + e.getMessage());
		}
	}
	
	public boolean setBlack(String ids) {
		try {
			LOGGER.debug("设置黑名单");
			String idStr []= ids.split(",");
			return bmlistMapper.setBlack(idStr)>0?true:false;
		} catch (Exception e) {
			throw new RuntimeException("恢复出现异常:" + e.getMessage());
		}
	}
	
	public boolean withdraw(String uid) {
		try {
			LOGGER.debug("services===="+uid);
			return bmlistMapper.withdrow(uid)>0?true:false;
		} catch (Exception e) {
			throw new RuntimeException("恢复出现异常:" + e.getMessage());
		}
	}
}