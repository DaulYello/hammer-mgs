import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';
//获取角色分页
export function getRolePage(page,size) {
  const data = {
    page,
    size
  };
  return fetch({
    url: '/role/select/PageRole' + getParams(data),
    method: 'get'
  });
}
//根据ID获取角色基本信息
export function getRoleById(id) {
  const data = {
    id
  };
  return fetch({
    url: '/role/select/Role' + getParams(data),
    method: 'get'
  });
}
//获取所有角色
export function getRoleAll() {
  return fetch({
    url: '/role/select/AllRole',
    method: 'get'
  });
}
//根据用户ID获取所有权限
export function getRoleByUserId(id) {
  const data = {
    id:id
  };
  return fetch({
    url: '/role/select/getRoleByUserId' + getParams(data),
    method: 'get'
  });
}
//根据角色ID获取所有权限
export function getPermissionByRoleId(id) {
  const data = {
    id:id
  };
  return fetch({
    url: '/role/select/getPermissionByRoleId' + getParams(data),
    method: 'get'
  });
}
//添加角色信息
export function addRole(obj) {
  return fetch({
    url: '/role/add/Role',
    method: 'POST',
    data:obj
  });
}
//addPermissionByRoleId
export function addPermissionByRoleId(id,obj) {
  const data = {
    rodeId:id,
    list:obj
  };
  return fetch({
    url: '/role/add/addPermissionByRoleId',
    method: 'POST',
    data
  });
}

//修改角色信息
export function putRole(obj) {
  return fetch({
    url: '/role/update/Role',
    method: 'PUT',
    data:obj
  });
}
//删除角色信息
export function delRole(id) {
  const data = {
    id:id
  };
  return fetch({
    url: '/role/delete/Role'+ getParams(data),
    method: 'delete'
  });
}
