import  from 'utils/fetch';
import {appRouter} from 'router/router';
import {
    getParams
} from 'utils/params';
//import VueRouter from "vue-router";

//获取公告信息分页
export function getNoticePage(pageNo,pageSize,query) {

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
        url: '/backManger/fmkj/HcNotices/selectNotices' +  getParams(data),
        method: 'get'
    });
}



// 发布公告
export function publishNotice(contents,title) {
    contents=encodeURIComponent(contents);
    const data = {
        title,
        contents
    }
    return fetch({
        url: '/backManger/fmkj/HcNotices/publishNotice' +  getParams(data),
        method: 'post'
    });
}

//修改公告信息
export function  editNotify(id,title,contents) {
    contents=encodeURIComponent(contents);
    const data = {
        id,
        title,
        contents
    }
    return fetch({
        url: '/backManger/fmkj/HcNotices/editNotifySys' +  getParams(data),
        method: 'post'
    });
}
//删除公告
export function deleteNotice (id) {
    const data = {
        id
    };
    console.log("shanchudeid="+id)
    return fetch({
        url: '/backManger/fmkj/HcNotices/deleteTicketById'+  getParams(data),
        method: 'post'
    });
}
