    import fetch from 'utils/fetch';
import {
    getParams
} from 'utils/params';
//用户信息查询
export function getUserPage(pageNo,pageSize,userStatus,query) {
    const data = {
        pageNo,
        pageSize,
        userStatus
    };
    for(var k in query){
        if (query[k] != "") {
            data[k] = query[k];
        }
    };
    return fetch({
        url: '/backManger/fmkj/HcAccount/queryUserInfo' +  getParams(data),
        method: 'get'
    });
}
// 拉入黑名单
export function shielding(id) {
    const data = {
        uid: id
    };
    return fetch({
        url: '/backManger/fmkj/BmList/drawBlackList' +  getParams(data),
        method: 'post'
    });
}
// 恢复
export function backUser(id) {
    const data = {
        uid: id
    };
    return fetch({
        url: '/backManger/fmkj/BmList/removeBlackList' +  getParams(data),
        method: 'post'
    });
}
// 设置白名单
export function setWhite(ids) {
    const data = {
        ids: ids
    };
    return fetch({
        url: '/backManger/fmkj/BmList/setWhite' +  getParams(data),
        method: 'post'
    });
}
// 设置黑名单
export function setBlack(ids) {
    const data = {
        ids: ids
    };
    return fetch({
        url: '/backManger/fmkj/BmList/setBlack' +  getParams(data),
        method: 'post'
    });
}
