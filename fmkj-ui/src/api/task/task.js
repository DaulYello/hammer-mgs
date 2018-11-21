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
    data.startDate=format(data.startDate, 'yyyy-MM-dd HH:mm:ss');
    data.endDate=format(data.endDate, 'yyyy-MM-dd HH:mm:ss');
    return fetch({
        url: '/backManger/fmkj/PmTask/addTask' +  getParams(data),
        method: 'post'
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