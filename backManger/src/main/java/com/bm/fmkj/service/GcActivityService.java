package com.bm.fmkj.service;

import com.alibaba.fastjson.JSON;
import com.bm.fmkj.base.PageQuery;
import com.bm.fmkj.base.PageUtil;
import com.bm.fmkj.base.Pagenation;
import com.bm.fmkj.constant.RecyleEnum;
import com.bm.fmkj.constant.TakeEnum;
import com.bm.fmkj.controller.GcActivityController;
import com.bm.fmkj.dao.*;
import com.bm.fmkj.utils.PropUtil;
import com.bm.fmkj.utils.StringUtils;
import com.fmkj.hammer.puzzle.Helper;
import com.fmkj.hammer.puzzle.Info;
import com.fmkj.hammer.puzzle.Person;
import com.fmkj.hammer.puzzle.State;
import com.github.pagehelper.PageHelper;
import com.xl.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.*;


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
	@Autowired
	private GcJoinactivityrecordMapper joinactivityrecordMapper;
	@Autowired
	private HcAccountMapper hcAccountMapper;
	@Autowired
	private FmRecyleLogMapper fmRecyleLogMapper;

	private Logger log= LoggerFactory.getLogger(GcActivityController.class);

	public GcActivityMapper getGcActivityMapper() {
		return gcactivityMapper;
	}

	/**
	 * 获取合约地址，进入参与状态
	 * @param ga
	 * @return
	 */
	public HashMap<String,Object> updateActivityAndContract(HashMap<String, Object> params) {
		try {
			HashMap<String,Object> map = new HashMap<>();
			log.debug("进入业务层");
			log.info("queryUserInfo-params={}", JSON.toJSONString(params));
			String idStr = (String)params.get("ids");
			log.info("queryUserInfo-params={}", params.get("ispass"));
			String ispass = (String)params.get("ispass");

			String ids [] = idStr.split(",");

			if ("1".equals(ispass)) {
				log.info("通过的标志======="+ispass);
				for (int i = 0; i < ids.length; i++) {
					log.debug("---------->"+ids[i]);
					GcActivity activity = gcactivityMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
					if(StringUtils.isNull(activity.getTypeid())){
						map.put("status",false);
						map.put("message","活动类型为NUll！活动id="+activity.getId());
						return map;
					}
					GcActivitytype gt = gcactivitytypeService.getGcActivitytypeId(activity.getTypeid());
					if(StringUtils.isNull(gt)){
						map.put("status",false);
						map.put("message","活动类型为Null！活动id="+activity.getId());
						return map;
					}
					activity.setStatus(2);
					log.debug("活动状态设为2，就进入参与状态了"+activity.getStatus());
					activity.setBegintime(DateUtil.getNowInMillis(0l));
					activity.setEndtime(DateUtil.plusDay(gt.getDays()));
					log.debug("通过产品的溢价除以参与活动的人数");

					//activity.setPar(Math.ceil(activity.getPremium()/activity.getNum()));
					long startTime1 = System.currentTimeMillis();
					Helper helper = new Helper(PropUtil.getString("contractPassword"),
							PropUtil.getString("keystoryPath"), PropUtil.getString("contractIp"),
							PropUtil.getString("contractPort"));
					boolean initR=helper.init();
					long times1 = System.currentTimeMillis() - startTime1;
					log.debug("合约地址加载================》》》》》：" + times1);
					if(!initR){
						map.put("status",false);
						map.put("message","初始化活动失败！活动id="+activity.getId());
						return map;
					}
					// 1.获得合约地址

					if(StringUtils.isNull(activity.getPar())){
						map.put("status",false);
						map.put("message","参与活动的面值为NULL！活动id="+activity.getId());
						return map;
					}
					if(StringUtils.isNull(activity.getNum())){
						map.put("status",false);
						map.put("message","设定参与活动的人数为Null！活动id="+activity.getId());
						return map;
					}
					Info info = new Info(BigInteger.valueOf(activity.getId()), BigInteger.valueOf(gt.getId()),
							BigInteger.valueOf(Integer.parseInt(new java.text.DecimalFormat("0").format(Math.ceil(activity.getPar())))), BigInteger.valueOf(activity.getNum()));

					if(StringUtils.isNull(activity.getName())){
						map.put("status",false);
						map.put("message","活动的名称NULL！活动id="+activity.getId());
						return map;
					}
					if(StringUtils.isNull(activity.getStartid())){
						map.put("status",false);
						map.put("message","设定参与活动的人数为Null！活动id="+activity.getId());
						return map;
					}

					Person origPerson = new Person(activity.getName(), BigInteger.valueOf(activity.getStartid()));

					String contract = helper.deployContract(info, origPerson);
					if(StringUtils.isNull(contract)){
						map.put("status",false);
						map.put("message","合约地址获取失败！活动id="+activity.getId());
						return map;
					}
					boolean result = helper.changeStage(State.participate);
					if(!result){
						map.put("status",false);
						map.put("message","更改合约状态为participate失败！活动id="+activity.getId());
						return map;
					}
					activity.setContract(contract);
					boolean updateResult= gcactivityMapper.updateByPrimaryKeySelective(activity) > 0 ? true : false;
					if(!updateResult){
						map.put("status",false);
						map.put("message","更改活动失败！活动id="+activity.getId());
						return map;
					}
					helper.release();
				}
				map.put("status",true);
				map.put("message","审核成功！");
				return map;
			}else {
				log.info("拒绝通过的标志======="+ispass);
				for (int i = 0; i < ids.length; i++) {
					GcActivity activity = new GcActivity();
					activity.setId(Integer.parseInt(ids[i]));
					activity.setStatus(1);
					boolean updateResult= gcactivityMapper.updateByPrimaryKeySelective(activity) > 0 ? true : false;
					if(!updateResult){
						map.put("status",false);
						map.put("message","更改活动失败！活动id="+activity.getId());
						return map;
					}
				}
				map.put("status",true);
				map.put("message","活动驳回成功！");
				return map;
			}
		} catch (Exception e) {
			throw new RuntimeException("操作失败："+e.getMessage());
		}
	}
	/**
	 * 更新合约状态  开始竟锤，接着获取优胜者。
	 * @param ga
	 * @return
	 */
	public HashMap<String,Object> updatepuzzleHammerStatus(HashMap<String, Object> params) {
		HashMap<String,Object> map = new HashMap<>();
		try {
			log.debug("前台传过来的字符串====》" + JSON.toJSONString(params));
			int status = Integer.parseInt((String)params.get("status"));
			int id = Integer.parseInt((String)params.get("id"));
			boolean flag = false;
			Helper helper = null;
			GcActivity activity = gcactivityMapper.selectByPrimaryKey(id);
			if(StringUtils.isNull(activity)){
				map.put("status",false);
				map.put("message","查询活动失败，活动id="+id);
				return map;
			}
			log.debug("1.获取地址" + activity.getContract());
			helper = new Helper(PropUtil.getString("contractPassword"),
					PropUtil.getString("keystoryPath"), PropUtil.getString("contractIp"),
					PropUtil.getString("contractPort"));
			boolean initR=helper.init();
			if(!initR){
				map.put("status",false);
				map.put("message","初始化活动失败！");
				return map;
			}
			if(StringUtils.isNull(activity.getContract())){
				map.put("status",false);
				map.put("message","初始化活动失败！");
				return map;
			}
			flag = helper.loadContract(activity.getContract());
			log.debug("2.加载合约地址,是否成功：" + flag);
			if(!flag){
				map.put("status",false);
				map.put("message","加载合约地址失败！");
				return map;
			}
			boolean result= false;
			boolean result2 = false;
			if (flag){
				result= helper.changeStage(State.notice);
				log.debug("活动进入竟锤状态："+result);
				if(!result){
					map.put("status",false);
					map.put("message","活动进入竟锤状态失败！");
					return map;
				}
				result= helper.puzzleWinner();
				if(!result){
					map.put("status",false);
					map.put("message","获取活动竟锤优胜者！");
					return map;
				}

				int hammerId = helper.getWinner().getID().intValue();
				if(hammerId<=0){
					map.put("status",false);
					map.put("message","获取优胜者的id失败！");
					return map;
				}
				activity.setGetid(hammerId);
				log.debug("优胜者的id:"+hammerId+"---姓名："+helper.getWinner().getName());
				boolean result3=helper.changeStage(State.end);
				if(result3){
					map.put("status",false);
					map.put("message","改变合约状态问end失败！");
					return map;
				}else{
					result2=saveNoticeInfo(activity);
				}
				log.debug("插入通知消息结果："+result2);
				if(!result2){
					map.put("status",false);
					map.put("message","插入通知消息结果失败！");
					return map;
				}else{
					activity.setStatus(3);//3是活动已经结束
					result2=gcactivityMapper.updateByPrimaryKeySelective(activity)>0?true:false;
				}
			}
			if(!result2){
				map.put("status",false);
				map.put("message","更新活动状态为'结束'失败！");
				return map;
			}
			map.put("status",true);
			map.put("message","活动竟锤成功！");
			return map;
		}catch (Exception e) {
			map.put("status",false);
			map.put("message","竟锤失败！");
			return map;
		}
	}


	public boolean saveNoticeInfo(GcActivity activity) {

		log.debug("保存优胜者的记录！gc_notice");
		try {
			GcActivitytype gcActivitytype =gcgctivitytypeMapper.selectByPrimaryKey(activity.getTypeid());
			GcNotice notice = new GcNotice();
			notice.setUid(activity.getGetid());
			//notice.setTime(new Date());
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
			//notice.setMessage(sb.toString());
			return gcNoticeMapper.insertSelective(notice)>0?true:false;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
	}

	public HashMap<String,Object> cancelActivity(HashMap<String, Object> params) {

		HashMap<String,Object> map = new HashMap<>();
		try {
			log.debug("cancelActivity进入业务层");
			log.info("queryUserInfo-params={}", JSON.toJSONString(params));
			String idStr = (String)params.get("ids");

			String ids [] = idStr.split(",");
			for(int i=0;i<ids.length;i++) {
				GcActivity activity = gcactivityMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
				if(StringUtils.isNull(activity)){
					map.put("status",false);
					map.put("message","查询活动失败，活动id="+Integer.parseInt(ids[i]));
					return map;
				}else if(StringUtils.isNull(activity.getPar())){
					map.put("status",false);
					map.put("message","参与活动的CNT为NULL，活动id="+Integer.parseInt(ids[i]));
					return map;
				}
				log.debug("将活动设为失败后，将参与活动的用户cnt退换");
				GcJoinactivityrecord joinactivityrecord = new GcJoinactivityrecord();
				joinactivityrecord.setAid(Integer.parseInt(ids[i]));
				List<GcJoinactivityrecord> joinactivityrecords = joinactivityrecordMapper.select(joinactivityrecord);
				if(joinactivityrecords.size()==0){
					boolean result = updateActivityStatus(activity);
					map.put("status",false);
					map.put("message","查询活动参与记录为0,该活动还没有人参与,取消成功！");
					continue;
				}
				log.debug("给参与活动的用户发放R积分");
				List<FmRecyleLog> recyleLogs = new ArrayList<>();
				//List<Integer> uids = new ArrayList<>();
				for(GcJoinactivityrecord gcJoinactivityrecord : joinactivityrecords){
					//uids.add(gcJoinactivityrecord.getUid());
					HcAccount account = hcAccountMapper.selectByPrimaryKey(gcJoinactivityrecord.getUid());
					account.setCnt(account.getCnt()+activity.getPar());
					account.setUpdateDate(new Date());
					boolean rowUpdate= hcAccountMapper.updateByPrimaryKeySelective(account)>0?true : false;
					if(!rowUpdate){
						log.info("活动名称为：" + activity.getPname() + "更新cnt报错");
						continue;
					}
					log.debug("记录活动失败用户反回的CNT，用户id="+gcJoinactivityrecord.getUid());
					FmRecyleLog recyleLog = new FmRecyleLog();
					recyleLog.setUid(gcJoinactivityrecord.getUid());
					recyleLog.setFriendId(gcJoinactivityrecord.getUid());
					recyleLog.setRecyleType(RecyleEnum.TYPE_R.status);
					recyleLog.setTakeDate(new Date());
					recyleLog.setTakeNum(activity.getPar());
					recyleLog.setTakeType(TakeEnum.FAIL_BACK.status);
					recyleLog.setTakeMsg("活动ID为【"+activity.getId()+"】的活动失败，将参加活动的CNT退还给参与用户");
					recyleLogs.add(recyleLog);
				}
				fmRecyleLogMapper.batchAddRecyleLog(recyleLogs);
				boolean result = updateActivityStatus(activity);
				if(!result){
					map.put("status",false);
					map.put("message","更新活动状态为5失败，活动id="+Integer.parseInt(ids[i]));
					return map;
				}
			}
			map.put("status",true);
			map.put("message","活动取消成功！");
			return map;
		} catch (NumberFormatException e) {
			throw new RuntimeException(e.getMessage());
		}
	}


	public List<GcActivity> queryActivityByStatus(){
		return gcactivityMapper.queryActivityByStatus();
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


	public void acitivtyFailBackCNT() {
		List<GcActivity> gcActivities = gcactivityMapper.queryActivityByStatus();
		for (GcActivity gcActivity : gcActivities) {
			Date now = new Date();
			if (gcActivity.getEndtime().getTime() - now.getTime() <= 0) {
				log.info("活动名称为：" + gcActivity.getPname() + "活动已过期，id号位：" + gcActivity.getId());
				GcJoinactivityrecord joinactivityrecord = new GcJoinactivityrecord();
				joinactivityrecord.setAid(gcActivity.getId());
				List<GcJoinactivityrecord> joinactivityrecords = joinactivityrecordMapper.select(joinactivityrecord);
				log.info("活动名称为：" + gcActivity.getPname() + "活动参与记录的人数" + joinactivityrecords.size());
				if (joinactivityrecords.size() <= 0) {
					boolean result = updateActivityStatus(gcActivity);
					if(!result){
						log.info("活动名称为：" + gcActivity.getPname() + "活动已更新失败");
					}
					log.info("活动名称为：" + gcActivity.getPname() + "活动已经到期也没有人参加这个活动");
					continue;
				}
				List<FmRecyleLog> recyleLogs = new ArrayList<>();
				//List<Integer> ids = new ArrayList<>();

				for (GcJoinactivityrecord gcJoinactivityrecord : joinactivityrecords) {
					//ids.add(gcJoinactivityrecord.getUid());
					HcAccount account = hcAccountMapper.selectByPrimaryKey(gcJoinactivityrecord.getUid());
					account.setCnt(account.getCnt()+gcActivity.getPar());
					account.setUpdateDate(new Date());
					boolean rowUpdate= hcAccountMapper.updateByPrimaryKeySelective(account)>0?true : false;
					if(!rowUpdate){
						log.info("活动名称为：" + gcActivity.getPname() + "更新cnt报错");
						continue;
					}
					FmRecyleLog recyleLog = new FmRecyleLog();
					recyleLog.setUid(gcJoinactivityrecord.getUid());
					recyleLog.setFriendId(gcJoinactivityrecord.getUid());
					recyleLog.setRecyleType(RecyleEnum.TYPE_CNT.status);
					recyleLog.setTakeDate(new Date());
					recyleLog.setTakeNum(gcActivity.getPar());
					recyleLog.setTakeType(TakeEnum.FAIL_BACK.status);
					recyleLog.setTakeMsg("活动流局，活动ID【" + gcActivity.getId() + "】退还用户" + gcActivity.getPar() + "CNT");
					recyleLogs.add(recyleLog);
				}
				log.debug("批量退还给参与互动的用户的cnt");
				/*HashMap<String,Object> param = new HashMap();
				param.put("uids",ids);
				param.put("par",gcActivity.getPar());
				int row= hcAccountMapper.updateUserCNTbyID(param);*/
				/*if(row<=0){
					log.info("更新用户的CNT失败！");
					throw new RuntimeException("更新用户的cnt时报错！事务回滚!");
				}*/
				fmRecyleLogMapper.batchAddRecyleLog(recyleLogs);
				boolean result = updateActivityStatus(gcActivity);
				if (!result) {
					log.info("更新活动状态为5失败，活动id:" + gcActivity.getId());
					throw new RuntimeException("更新用户的cnt时报错！事务回滚!");
				}
			}
		}
	}

	public boolean updateActivityStatus(GcActivity activity){
		log.debug("将活动设为失败状态5");
		activity.setStatus(5);
		activity.setEndtime(new Date());
		return gcactivityMapper.updateByPrimaryKeySelective(activity) > 0 ? true : false;
	}

	public HashMap<String,Object> blockChainMeltDetail(HashMap<String, Object> params) {
		HashMap<String,Object> map = new HashMap<>();
		try{
			log.debug("1.查询那些活动还在进行中");
			List<GcActivity> activities = gcactivityMapper.queryActivityByStatus();
			if (activities.size() == 0){
				log.debug("没有在进行中的活动，请查看数据库！");
				map.put("message","没有在进行中的活动，请查看数据库！");
				map.put("result",false);
				return map;
			}
			List<Integer> list = new ArrayList<>();
			for(GcActivity activity : activities){
				list.add(activity.getId());
			}
			log.debug("2.通过还在进行中的活动id更新旗下的所有上链状态isChain为0，目的是重新在新的合约上链");
			boolean result = joinactivityrecordMapper.updateJoinRecodeIsChain(list) > 0 ? true : false;
			if (!result){
				log.debug("没有在进行中的活动，请查看数据库！");
				map.put("message","2.通过还在进行中的活动id更新旗下的所有上链状态isChain为0，失败！");
				map.put("result",false);
				return map;
			}
			log.debug("3.将这些在进行中的活动状态都设为0，从新审核");
			boolean result_a = gcactivityMapper.updateActivte(list) > 0 ? true : false;
			if (!result){
				log.debug("3.将这些在进行中的活动状态都设为0，从新审核！");
				map.put("message","3.将这些在进行中的活动状态都设为0，从新审核，失败！");
				map.put("result",false);
				return map;
			}
			map.put("message","处理成功！");
			map.put("result",true);
			return map;
		}catch (RuntimeException e){
			map.put("message",e.getMessage());
			map.put("result",false);
			return map;
		}
	}
}