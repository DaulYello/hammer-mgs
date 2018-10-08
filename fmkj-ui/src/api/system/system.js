import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';
//获取任务列表
export function getQuartzPage(pageNo,pageSize, query) {
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
        url: '/backManger/fmkj/job/queryJobPage' + getParams(data),
        method: 'get'
    });
}

// 获取调度日志
export function getQuartLogPage(pageNo,pageSize, query) {
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
        url: '/backManger/fmkj/job/queryJobLogPage' + getParams(data),
        method: 'get'
    });
}

//删除调度日志
export function deleteQuartzLog (ids) {
    console.log('idsidsids:' + ids)
    const data = {
        ids
    };
    return fetch({
        url: '/backManger/fmkj/job/deleteJobLog'+  getParams(data),
        method: 'get'
    });
}

export function addQuartz(data) {
    return fetch({
        url: '/backManger/fmkj/job/addJob' +  getParams(data),
        method: 'post'
    });
}

//删除任务
export function deleteQuartz (ids) {
    const data = {
        ids
    };
    return fetch({
        url: '/backManger/fmkj/job/deleteJob'+  getParams(data),
        method: 'get'
    });
}
//修改
export function updateQuartz(obj) {
    const data = {
        jobId: obj.jobId,
        jobName: obj.jobName,
        jobGroup: obj.jobGroup,
        methodName: obj.methodName,
        methodParams: obj.methodParams,
        cronExpression: obj.cronExpression,
        misfirePolicy: obj.misfirePolicy
    };
    return fetch({
        url: '/backManger/fmkj/job/updateJob' +  getParams(data),
        method: 'post'
    });
}

// 启用
export function changeStatus(jobId, changeValue){
    const data = {
        jobId: jobId,
        status: changeValue
    };
    return fetch({
        url: '/backManger/fmkj/job/changeStatus' + getParams(data),
        method: 'post'
    });
}
// 执行
export function runQuartz(jobId){
    const data = {
        jobId: jobId
    };
    return fetch({
        url: '/backManger/fmkj/job/runJob' + getParams(data),
        method: 'post'
    });
}

//业务操作日志的查询
export function getQueryOperaLog(pageNo,pageSize,query){
    console.log("业务操作日志的查询")
    const data = {
        pageNo,
        pageSize
    }
    for (var k in query){
        if (query[k] !=""){
            data[k]=query[k];
        }
    }
    return fetch({
        url: '/backManger/fmkj/SysOperateLog/getQueryOperaLog' + getParams(data),
        method: 'get'
    });
}
//删除业务操作日志
export function deleteOperaLog(ids){
    console.log('idsidsids:' + ids)
    const data = {
        ids
    };
    return fetch({
        url: '/backManger/fmkj/SysOperateLog/deleteOperaLog'+  getParams(data),
        method: 'get'
    });
}


