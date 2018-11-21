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


// 获取调度日志
export function getStrategyPage(pageNo,pageSize, query) {
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
        url: '/backManger/fmkj/strategy/getStrategyPage' + getParams(data),
        method: 'get'
    });
}

//删除调度日志
export function deleteStrategy (ids) {
    const data = {
        ids
    };
    return fetch({
        url: '/backManger/fmkj/strategy/deleteStrategy'+  getParams(data),
        method: 'get'
    });
}

export function addStrategy(obj) {
    const data = {
        tid: obj.tid,
        strategy: obj.strategyText,
        order: obj.strategyOrder,
        imageUrl: obj.strategyImage
    };
    return fetch({
        url: '/backManger/fmkj/strategy/addStrategy' +  getParams(data),
        method: 'post'
    });
}