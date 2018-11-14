import fetch from 'utils/fetch';
import {
    getParams
} from 'utils/params';

//获取商品列表
export function getGoodsPage(pageNo,pageSize, query) {
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