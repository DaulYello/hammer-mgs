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
        url: '/backManger/fmkj/PmTask/getTaskList' + getParams(data),
        method: 'get'
    });
}
//添加任务或者修改任务
export function taskAddAndModify(data) {
    data.startDate=format(data.startDate, 'yyyy-MM-dd HH:mm:ss');
    data.endDate=format(data.endDate, 'yyyy-MM-dd HH:mm:ss');
    var urlStr = '';
    if(data.id != ''){
        console.log("修改任务数据id="+data.id);
        urlStr = '/backManger/fmkj/PmTask/updateTask' +  getParams(data);
    }else{
        console.log("新增任务数据id="+data.id);
        urlStr = '/backManger/fmkj/PmTask/addTask' +  getParams(data);
    }

    return fetch({
        url: urlStr,
        method: 'post'
    });
}
//编辑任务
export function updateTask(task) {
    console.debug("方法参数："+task.methodParams);
    const data = {
        id:task.id,
        title: task.title,
        taskTarget: task.taskTarget,
        jobGroup: task.jobGroup,
        subDesc: task.subDesc,
        reward: task.reward,
        auditCycle: task.auditCycle,
        downUrl: task.downUrl
    };
    return fetch({
        url: '/backManger/fmkj/PmTask/updateTask' +  getParams(data),
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
export function deleteTask(id) {
    const data = {
        id
    };
    return fetch({
        url: '/backManger/fmkj/PmTask/deleteTask' + getParams(data),
        method: 'post'
    });

}


// 获取攻略
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

//删除攻略
export function deleteStrategy (ids) {
    const data = {
        ids
    };
    return fetch({
        url: '/backManger/fmkj/strategy/deleteStrategy'+  getParams(data),
        method: 'get'
    });
}

export function addStrategy(obj, imageId) {
    const data = {
        tid: obj.tid,
        strategy: obj.strategyText,
        orderNum: obj.strategyOrder,
        imageId: imageId
    };
    return fetch({
        url: '/backManger/fmkj/strategy/addStrategy' +  getParams(data),
        method: 'post'
    });
}


// 获取参与记录
export function getPartPage(pageNo,pageSize, query) {
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
        url: '/backManger/fmkj/part/getPartPage' + getParams(data),
        method: 'get'
    });
}

export function auditPart(auditOption, id, auditStatus) {
    const data = {
        auditOption: auditOption,
        id: id,
        auditStatus: auditStatus
    };
    return fetch({
        url: '/backManger/fmkj/part/auditPart' +  getParams(data),
        method: 'post'
    });
}

