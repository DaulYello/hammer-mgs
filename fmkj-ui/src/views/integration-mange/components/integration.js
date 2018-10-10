import fetch from 'utils/fetch';
import {
    getParams
} from 'utils/params';

//获取任务列表
export function queryIntegral(pageNo,pageSize) {
    const data = {
        pageNo,
        pageSize
    };
    /*for(var k in query){
        if (query[k] != "") {
            data[k] = query[k];
        }
    };*/
    return fetch({
        url: '/backManger/fmkj/FmRpool/queryIntegral' + getParams(data),
        method: 'get'
    });
}