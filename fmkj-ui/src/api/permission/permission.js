import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';
//获取系统分页
export function getPermissionPage(page,size) {
  const data = {
    page,
    size
  };
  return fetch({
    url: '/permission/select/PagePermission' + getParams(data),
    method: 'get'
  });
}
//根据ID获取权限基本信息
export function getPermissionById(id) {
  const data = {
    id
  };
  return fetch({
    url: '/permission/select/Permission' + getParams(data),
    method: 'get'
  });
}
//根据系统ID获取权限基本信息
export function getPermissionBySystemId(id) {
  const data = {
    system_id:id
  };
  return fetch({
    url: '/permission/select/AllPermission' + getParams(data),
    method: 'get'
  });
}
//添加权限信息
export function addPermission(obj) {
  return fetch({
    url: '/permission/add/Permission',
    method: 'POST',
    data:obj
  });
}
//添加权限信息
export function addPermissionAuto(obj) {
  return fetch({
    url: '/permission/add/PermissionAuto',
    method: 'POST',
    data:obj
  });
}

//修改权限信息
export function putPermission(obj) {
  return fetch({
    url: '/permission/update/Permission',
    method: 'PUT',
    data:obj
  });
}
//删除权限信息
export function delPermission(id) {
  const data = {
    id:id
  };
  return fetch({
    url: '/permission/delete/PermissionAuto'+ getParams(data),
    method: 'delete'
  });
}
