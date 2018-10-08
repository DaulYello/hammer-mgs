import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';

//获取名单信息分页
export function getBlackPage(pageNo,pageSize,status, query) {
  const data = {
    pageNo,
    pageSize,
    status
  };
  for(var k in query){
      if(query[k] !="") {
          data[k] = query[k];
      }
  };
  return fetch({
      url: '/backManger/fmkj/BmList/queryUserByExample' +  getParams(data),
    method: 'get'
  });
}
//撤回
export function undo(id, status) {
  console.log('----------ID:' + id)
  console.log('status:' + status)
    const data = {
        uid: id,
        status: status
    };
    return fetch({
        url: '/backManger/fmkj/BmList/withdraw' +  getParams(data),
        method: 'post'
    });
}

