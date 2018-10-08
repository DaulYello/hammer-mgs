import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';
//获取角色分页
export function getUserPage(page,size) {
  const data = {
    page,
    size
  };
  return fetch({
    url: '/user/select/PageUser' + getParams(data),
    method: 'get'
  });
}
//根据ID获取角色基本信息
export function getUserById(id) {
  const data = {
    id
  };
  return fetch({
    url: '/user/select/User' + getParams(data),
    method: 'get'
  });
}
//根据角色ID获取角色基本信息
export function getUserBySystemId(id) {
  const data = {
    system_id:id
  };
  return fetch({
    url: '/user/select/AllUser' + getParams(data),
    method: 'get'
  });
}
//根据用户ID获取所有权限
export function getPermissionByUserId(id) {
  const data = {
    id:id
  };
  return fetch({
    url: '/user/select/getPermissionByUserId' + getParams(data),
    method: 'get'
  });
}
export function getPermissionByUserIdWithOutRole(id) {
  const data = {
    id:id
  };
  return fetch({
    url: '/user/select/getPermissionByUserIdWithOutRole' + getParams(data),
    method: 'get'
  });
}
getPermissionByUserIdWithOutRole
//添加角色信息
export function addUser(obj) {
  return fetch({
    url: '/user/add/User',
    method: 'POST',
    data:obj
  });
}
//addPermissionByUserId
export function addRoleByUserId(id,obj) {
  const data = {
    userId:id,
    list:obj
  };
  return fetch({
    url: '/user/add/addRoleByUserId',
    method: 'POST',
    data
  });
}
//addPermissionByUserId
export function addPermissionByUserId(id,obj) {
  const data = {
    rodeId:id,
    list:obj
  };
  return fetch({
    url: '/user/add/addPermissionByUserId',
    method: 'POST',
    data
  });
}

//修改角色信息
export function putUser(obj) {
  return fetch({
    url: '/user/update/User',
    method: 'PUT',
    data:obj
  });
}
//删除角色信息
export function delUser(id) {
  const data = {
    id:id
  };
  return fetch({
    url: '/user/delete/UserAuto'+ getParams(data),
    method: 'delete'
  });
}
