import fetch from 'utils/fetch';
import {getParams} from "../../../utils/params";

/**
 * @declare 获取用户的实名认证的信息
 * @author  huangshuang
 * @created 2018-09-26
 */

export function getUserRealInfo(pageNo,pageSize,query){
    const data = {
        pageNo,
        pageSize
    }
    for(var k in query){
        if(query[k]!=""){
            data[k]=query[k];
        }
    }
    return fetch({
        url:'/backManger/fmkj/HcUserImage/getUserRealInfo'+getParams(data),
        method:'get'
    });
}

/**
 @declare 身份审核
 @param id hc_userimage的id，status(1.代表身份审核通过2.代表驳回身份审核)
 @author  huangshuang
 @created 2018-09-26
*/
export function identityCardAudit(id,flag,query){
    const data = {
        id,
        flag
    };
    for(var k in query){
        if(query[k] !=""){
            data[k] = query[k];
        }
    };
    return fetch({
        url:'/backManger/fmkj/HcUserImage/approveUserRealInfo'+getParams(data),
        method:'post'
    });
}

/**
 @declare 驳回身份审核
 @author  huangshuang
 @created 2018-09-26
 */
export function identityCardRefuse(id){
    const data = {
        id
    }
    return fetch({
        url:'/backManger/fmkj/HcUserImage/identityCardRefuse'+getParams(data),
        method:'post'
    });
}