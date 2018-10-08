import fetch from 'utils/fetch';
import {
    getParams
} from 'utils/params';
//黑名单用户信息查询
export function getBlackUser(pageNo,pageSize,query) {
    const data = {
        pageNo,
        pageSize
    };
    for(var k in query){
        data[k] = query[k];
    };
    return fetch({
        url: '/backManger/fmkj/HcAccount/queryUserBlack' +  getParams(data),
        method: 'get'
    });
}

// 恢复
export function recover(id) {
    const data = {
        uid: id
    };
    return fetch({
        url: '/backManger/fmkj/BmList/removeBlackList' +  getParams(data),
        method: 'post'
    });

}
