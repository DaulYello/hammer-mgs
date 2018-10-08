import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';

//获取门票信息分页
export function getTicketPage(pageNo,pageSize,query) {

  const data = {
    pageNo,
    pageSize
  };
  for(var k in query){
      if (query[k] != "") {
          data[k] = query[k];
      }
  };
  return fetch({
      url: '/backManger/fmkj/GcTicket/selectAllTicket' +  getParams(data),
      method: 'get'
  });
}

// 新建门票
export function add(data) {
    return fetch({
        url: '/backManger/fmkj/GcTicket/buildNewTicket' +  getParams(data),
        method: 'post'
    });
}

//修改门票
export function update(obj) {
    const data = {
        id: obj.id,
        type: obj.type,
        name: obj.name,
        price: obj.price
    };
    return fetch({
        url: '/backManger/fmkj/GcTicket/updateTicketById' +  getParams(data),
        method: 'post'
    });
}
//删除门票
export function deleteTiket (id) {
    const data = {
        id
    };
    return fetch({
        url: '/backManger/fmkj/GcTicket/deleteTicketById'+  getParams(data),
        method: 'get'
    });
}

