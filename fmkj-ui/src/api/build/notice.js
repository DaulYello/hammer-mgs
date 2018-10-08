import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';
//获取所有楼宇公告信息
export function getNoticePage(page,size,query) {
  const data = {
    page,
    size
  };
  for(var k in query){
    data[k] = query[k];
  };
  return fetch({
    url: 'http://api.bangjiat.com/app/system/notice/select/PageNotice' + getParams(data),
    method: 'get'
  });
}
//添加楼宇公告信息
export function addNotice(obj) {
  return fetch({
    url: 'http://api.bangjiat.com/app/system/notice/add/Notice',
    method: 'POST',
    data:obj
  });
}
//修改楼宇公告信息
export function putNotice(obj) {
  return fetch({
    url: 'http://api.bangjiat.com/app/system/notice/update/Notice',
    method: 'PUT',
    data:obj
  });
}
//删除楼宇公告信息
export function delNotice(id) {
  const data = {
    id:id+''
  };
  return fetch({
    url: 'http://api.bangjiat.com/app/system/notice/delete/Notice',//+ getParams(data),
    method: 'delete',
    data
  });
}
