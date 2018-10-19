import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';

//分页查询活动
export function queryActivityPage(pageNo,pageSize,status, query) {
  const data = {
    pageNo,
    pageSize,
    status
  };
  for(var k in query){
      if(query[k] !=""){
          data[k] = query[k];
      }
  };
  return fetch({
      url: '/backManger/fmkj/GcActivity/queryAllActivityByPage' +  getParams(data),
    method: 'get'
  });
}

// 批量审核
export function batchAuditAcitity(ids,pass) {
    const data = {
        ids: ids,
        ispass:pass
    };
    return fetch({
        url: '/backManger/fmkj/GcActivity/auditActivity' +  getParams(data),
        method: 'post'
    });
}

// 批量驳回
export function batchRefuseAcitity(ids) {
    const data = {
        ids: ids
    };
    return fetch({
        url:'/backManger/fmkj/GcActivity/auditActivity' +  getParams(data),
        method: 'post'
    });
}

//根据不同的状态执行
export function excuteActivity(id, status) {
    const data = {
        id,
        status
    };
    return fetch({
        url: '/backManger/fmkj/GcActivity/updatepuzzleHammerState' +  getParams(data),
        method: 'post'
    });
}

// 取消活动
export function batchCancel(ids) {
    const data = {
        ids: ids
    };
    return fetch({
        url: '/backManger/fmkj/GcActivity/cancelActivity' +  getParams(data),
        method: 'post'
    });
}

// 结束
export function batchEnd(ids) {
    const data = {
        ids: ids
    };
    return fetch({
        url: '/backManger/fmkj/GcActivity/endActivity' +  getParams(data),
        method: 'post'
    });
}
