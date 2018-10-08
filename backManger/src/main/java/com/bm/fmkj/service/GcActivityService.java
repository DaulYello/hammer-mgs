package com.bm.fmkj.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.net.ssl.ExtendedSSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.controller.GcActivityController;
import com.bm.fmkj.dao.GcActivity;
import com.bm.fmkj.dao.GcActivityMapper;
import com.bm.fmkj.dao.GcActivitytype;
import com.bm.fmkj.dao.GcActivitytypeMapper;
import com.bm.fmkj.dao.GcNotice;
import com.bm.fmkj.dao.GcNoticeMapper;
import com.bm.fmkj.utils.PropUtil;
import com.fmkj.hammer.puzzle.Helper;
import com.fmkj.hammer.puzzle.Info;
import com.fmkj.hammer.puzzle.Person;
import com.fmkj.hammer.puzzle.State;
import com.github.pagehelper.PageHelper;
import com.xl.utils.DateUtil;


@Transactional
@Service
public class GcActivityService {
	@Autowired
	private GcActivityMapper gcactivityMapper;
	@Autowired
	private GcActivitytypeMapper gcgctivitytypeMapper;
	@Autowired
	private GcActivitytypeService gcactivitytypeService;
	@Autowired
	private GcNoticeMapper gcNoticeMapper;

	private Logger log= LoggerFactory.getLogger(GcActivityController.class);
	
	public GcActivityMapper getGcActivityMapper() {
		return gcactivityMapper;
	}
	
	/**
	 * 获取合约地址，进入参与状态
	 * @param ga
	 * @return
	 */
	public boolean updateActivityAndContract(HashMap<String, Object> params) {
		try {
			log.debug("进入业务层");
			log.info("queryUserInfo-params={}", JSON.toJSONString(params));
			String idStr = (String)params.get("ids");
			log.info("queryUserInfo-params={}", params.get("ispass"));
			String ispass = (String)params.get("ispass");
			
			String ids [] = idStr.split(",");

			int rows = 0;
			if ("1".equals(ispass)) {
				log.info("通过的标志======="+ispass);
				for (int i = 0; i < ids.length; i++) {
					log.debug("---------->"+ids[i]);
					GcActivity activity = gcactivityMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
					GcActivitytype gt = gcactivitytypeService.getGcActivitytypeId(activity.getTypeid());
					activity.setStatus(4);
					activity.setIspass(1);
					log.debug("通过产品的溢价除以参与活动的人数");
					double premium = activity.getPnumber();
					int minnum = gt.getMinnum();
					activity.setPar((int)Math.ceil(premium/minnum));
					
					Helper helper = new Helper(PropUtil.getString("contractPassword"),
							PropUtil.getString("keystoryPath"), PropUtil.getString("contractIp"),
							PropUtil.getString("contractPort"));
					helper.init();
					
					// 1.获得合约地址
					Info info = new Info(BigInteger.valueOf(activity.getId()), BigInteger.valueOf(gt.getId()),
							BigInteger.valueOf(activity.getPar()), BigInteger.valueOf(gt.getMinnum()));
					
					Person origPerson = new Person(activity.getPname(), BigInteger.valueOf(activity.getStartid()));
					
					String contract = helper.deployContract(info, origPerson);
					log.debug("合约地址：" + contract);
					
					if(contract != null) {
						activity.setContract(contract);
						activity.setPname(null);//是因为把用户的姓名放在个字段传到后天。
						int row= gcactivityMapper.updateByPrimaryKeySelective(activity);
						rows+=row;
					}
				}
				if(rows>0) {
					return true;
				}
				return false;
			}else {
				log.info("拒绝通过的标志======="+ispass);
				for (int i = 0; i < ids.length; i++) {
					GcActivity activity = new GcActivity();
					activity.setId(Integer.parseInt(ids[i]));
					activity.setStatus(0);
					activity.setIspass(2);
					int row = gcactivityMapper.updateByPrimaryKeySelective(activity);
					rows+=row;
				}
				if(rows>0) {
					return true;
				}
				return false;
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException("操作失败："+e.getMessage());
		}
	}
	/**
	 * 更新合约状态
	 * @param ga
	 * @return
	 */
	public boolean updatepuzzleHammerStatus(HashMap<String, Object> params) {
		
		try {
			log.debug("前台传过来的字符串====》" + JSON.toJSONString(params));
			int status = Integer.parseInt((String)params.get("status"));
			int id = Integer.parseInt((String)params.get("id"));
			boolean flag = false;
			Helper helper = null;
			GcActivity activity = gcactivityMapper.selectByPrimaryKey(id);
			log.debug("1.获取地址" + activity.getContract());
			helper = new Helper(PropUtil.getString("contractPassword"),
					PropUtil.getString("keystoryPath"), PropUtil.getString("contractIp"),
					PropUtil.getString("contractPort"));
			helper.init();
			flag = helper.loadContract(activity.getContract());
			log.debug("2.加载合约地址,是否成功：" + flag);
			boolean result= false;
			boolean result2 = false;
			if(flag) {
				switch(status) {
					case 4:
						log.debug("活动刚上线状态,通过此接口进入参与状态");
						result= helper.changeStage(State.participate);
						if(result) {
							GcActivitytype gt = gcactivitytypeService.getGcActivitytypeId(activity.getTypeid());
							activity.setStatus(1);//参与状态
							activity.setBegintime(DateUtil.getNowInMillis(0l));
							activity.setEndtime(DateUtil.plusDay(gt.getDays()));
							result2=gcactivityMapper.updateByPrimaryKeySelective(activity)>0?true:false;
						}
						break;
					case 5:
						log.debug("活动进入close，通过此接口进入参与状态noticestatus");
						result= helper.changeStage(State.notice);
						if(result) {
							activity.setStatus(6);//活动进入notice
							result2=gcactivityMapper.updateByPrimaryKeySelective(activity)>0?true:false;
						}
						break;
					case 6:
						log.debug("获取活动竟锤优胜者");
						result= helper.puzzleWinner();
						if(result) {
							activity.setStatus(7);
							int hammerId = helper.getWinner().getID().intValue();
							activity.setGetid(hammerId);
							log.debug("优胜者的id:"+hammerId+"---姓名："+helper.getWinner().getName());
							boolean result3=helper.changeStage(State.end);
							log.debug("将金锤状态给为end:"+result3);
							if(result3) {
								result2=saveNoticeInfo(activity);
							}
							if(result2) {
								log.debug("插入通知消息结果："+result2);
								result2=gcactivityMapper.updateByPrimaryKeySelective(activity)>0?true:false;
							}
						}
						break;
					default:
				}
			}
			return result2;
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public boolean saveNoticeInfo(GcActivity activity) {
		
		log.debug("保存优胜者的记录！gc_notice");
		try {
			GcActivitytype gcActivitytype =gcgctivitytypeMapper.selectByPrimaryKey(activity.getTypeid());
			GcNotice notice = new GcNotice();
			notice.setUid(activity.getGetid());
			notice.setTime(new Date());
			notice.setFlag(1);
			StringBuilder sb = new StringBuilder();
			String head = new String(PropUtil.getString("head").getBytes("ISO-8859-1"),  "UTF-8");
			String message=new String(PropUtil.getString("message").getBytes("ISO-8859-1"),"UTF-8");
			sb.append(head);
			sb.append(gcActivitytype.getType());
			sb.append("—"+activity.getPname());
			sb.append(message);
			String messageStr =head+gcActivitytype.getType()+"—"+activity.getPname()+message;
			log.debug("中锤成功后提示信息的头部：-----》"+head);
			log.debug("中锤成功后提示信息：-----》"+message);
			log.debug(sb.toString());
			notice.setMessage(sb.toString());
			return gcNoticeMapper.insertSelective(notice)>0?true:false;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
	}
	
	public boolean cancelActivity(HashMap<String, Object> params) {
		
		try {
			log.debug("cancelActivity进入业务层");
			log.info("queryUserInfo-params={}", JSON.toJSONString(params));
			String idStr = (String)params.get("ids");
			
			String ids [] = idStr.split(",");
			GcActivity activity = new GcActivity();
			int rows = 0;
			for(int i=0;i<ids.length;i++) {
				activity = gcactivityMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
				log.debug("1.获取地址" + activity.getContract());
				activity.setId(Integer.parseInt(ids[i]));
				activity.setStatus(8);
				activity.setEndtime(new Date());
				Helper helper = new Helper(PropUtil.getString("contractPassword"),
						PropUtil.getString("keystoryPath"), PropUtil.getString("contractIp"),
						PropUtil.getString("contractPort"));
				helper.init();
				boolean result=helper.changeStage(State.cancle);
				log.debug("取消活动，改变合约状态----》："+result);
				int row = 0;
				if(result) {
					row = gcactivityMapper.updateByPrimaryKeySelective(activity);
				}
				rows+=row;
			}
			if(rows == ids.length) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public boolean endActivity(HashMap<String, Object> params) {
		
		try {
			log.debug("endActivity进入业务层");
			log.info("queryUserInfo-params={}", JSON.toJSONString(params));
			String idStr = (String)params.get("ids");
			
			String ids [] = idStr.split(",");
			GcActivity activity = new GcActivity();
			int rows = 0;
			for(int i=0;i<ids.length;i++) {
				activity.setId(Integer.parseInt(ids[i]));
				activity.setStatus(2);
				activity.setEndtime(new Date());
				int row = gcactivityMapper.updateByPrimaryKeySelective(activity);
				rows+=row;
			}
			if(rows == ids.length) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 活动分页查询
	 */
	public Pagenation<GcActivity> queryAllActivityByPage(PageQuery pageQuery){
		try {
			PageHelper.startPage(pageQuery.getPageNo(), pageQuery.getPageSize());
			List<GcActivity> activities = gcactivityMapper.queryAllActivityByPage(pageQuery.getParam());
			return PageUtil.toPagedResult(activities);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 通过id查询活动
	 */
	public GcActivity queryOneActivityById(GcActivity ga) {
		return gcactivityMapper.selectOne(ga);
	}
	
	/**
	 * 通过传入id及参数修改活动信息
	 */
	public int updateActivityById(GcActivity ga) {
		return gcactivityMapper.updateByPrimaryKeySelective(ga);
	}

}