import fetch from 'utils/fetch';
import {
    getParams
} from 'utils/params';

//获取任务列表
export function getTaskList(pageNo,pageSize, query) {
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
        url: '/backManger/fmkj/PmTask/getTaskList' + getParams(data),
        method: 'get'
    });
}
//添加任务
export function addTask(data) {
    return fetch({
        url: '/backManger/fmkj/PmTask/addTask' +  getParams(data),
        method: 'post'
    });
}

//删除任务
export function deleteTask(pageNo,pageSize, query) {
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
        url: '/backManger/fmkj/FmProductInfo/queryGoodsList' + getParams(data),
        method: 'get'
    });
}