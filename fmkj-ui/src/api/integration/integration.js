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
    return fetch({
        url: '/backManger/fmkj/FmRpool/queryIntegral' + getParams(data),
        method: 'get'
    });
}

/**
 * 查询积分规则
 * @param pageNo
 * @param pageSize
 */
export function queryIntegralRule(pageNo,pageSize) {
    console.log()
    const data = {
        pageNo,
        pageSize
    };
    return fetch({
        url: '/backManger/fmkj/FmReleaseRule/queryIntegralRule' + getParams(data),
        method: 'get'
    });
}

//删除
export function dropIntegration(id) {
    console.log('idsidsids:' + id)
    const data = {
        id
    };
    return fetch({
        url: '/backManger/fmkj/FmRpool/dropIntegration' + getParams(data),
        method: 'get'
    });
}

/**
 * 删除积分规则
 * @param id
 */
export function dropIntegrationRule(id) {
    console.log('come in ~:' + id)
    const data = {
        id
    };
    return fetch({
        url: '/backManger/fmkj/FmReleaseRule/dropIntegrationRule' + getParams(data),
        method: 'get'
    });
}


//修改
export function editIntegral(integration) {
    console.debug("方法参数："+integration.id);
    const data = {
        id: integration.id,
        rintegralNum: integration.rintegralNum,
        recycleNum: integration.recycleNum
    };
    return fetch({
        url: '/backManger/fmkj/FmRpool/editIntegral' + getParams(data),
        method: 'post'
    });
}

/**
 * 修改积分规则
 * @param integration
 */
export function editIntegralRule(fmReleaseRule) {
    console.debug("描述："+fmReleaseRule.allotDesc);
    const data = {
        id: fmReleaseRule.id,
        fyMin: fmReleaseRule.fyMin,
        fyMax: fmReleaseRule.fyMax,
        weight: fmReleaseRule.weight,
        allotPercent: fmReleaseRule.allotPercent,
        dilutPercent: fmReleaseRule.dilutPercent,
        allotDesc: fmReleaseRule.allotDesc
    };
    return fetch({
        url: '/backManger/fmkj/FmReleaseRule/editIntegralRule' + getParams(data),
        method: 'post'
    });
}

//新增
export function addIntegrate(data) {
    console.debug("come in"+data.toString());
    return fetch({
        url: '/backManger/fmkj/FmRpool/addIntegrate' +  getParams(data),
        method: 'post'
    });
}