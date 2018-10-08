import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';
//获取停车场信息分页
export function getCarparkPage(page,size) {
  const data = {
    page,
    size
  };
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/select/CarparkSpacePage' + getParams(data),
    method: 'get'
  });
}
//根据ID获取停车场基本信息
export function getCarparkById(id) {
  const data = {
    id
  };
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/select/Carpark' + getParams(data),
    method: 'get'
  });
}
//根据ID获取停车场管理者信息 page
export function getCarparkManagesById(spaceId) {
  const data = {
    spaceId
  };
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/select/SpaceUserList' + getParams(data),
    method: 'get'
  });
}
//添加停车场管理信息
export function addCarparkManage(spaceId,username) {
  const data = {
    spaceId,
    username
  };
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/add/SpaceUser',
    method: 'POST',
    data
  });
}
//删除停车场管理者
export function delCarparkManage(spaceId,userId) {
  const data = {
    spaceId,
    userId
  };
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/delete/SpaceUser',
    method: 'DELETE',
    data
  });
}

//添加停车场基本信息
export function addCarpark(obj) {
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/add/CarparkSpace',
    method: 'POST',
    data:obj
  });
}

//修改停车场基本信息
export function putCarpark(obj) {
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/update/CarparkSpace',
    method: 'PUT',
    data:obj
  });
}
//删除停车场信息
export function delCarpark(id) {
  return fetch({
    url: 'http://api.bangjiat.com/app/system/carpark/delete/CarparkSpace',
    method: 'delete',
    id:id
  });
}
