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
    if(query.starttime != undefined && query.endtime != undefined && query.starttime != '' && query.endtime != '' && query.starttime != 'NaN-NaN-NaN NaN:NaN:NaN' && query.endtime != 'NaN-NaN-NaN NaN:NaN:NaN'){
        query.starttime = format(query.starttime, 'yyyy-MM-dd HH:mm:ss');
        query.endtime = format(query.endtime, 'yyyy-MM-dd HH:mm:ss');
    }
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

var format = function(time, format)
{
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : "") + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
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
