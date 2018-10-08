import fetch from 'utils/fetch';
import {
  getParams
} from 'utils/params';
//获取所有轮播图
export function getCarouselPage(page,size,query) {
  const data = {
    page,
    size
  };
  for(var k in query){
    data[k] = query[k];
  };
  return fetch({
    // url: 'http://api.bangjiat.com/app/system/system/select/CarouselPage' + getParams(data),
    url: 'http://api.bangjiat.com/app/system/carousel/select/CarouselPage',
    method: 'get'
  });
}
//添加轮播图
export function addCarousel(obj) {
  return fetch({
    // url: 'http://api.bangjiat.com/app/system/system/add/AppCarousel',
    url: 'http://api.bangjiat.com/app/system/carousel/add/AppCarousel',
    method: 'POST',
    data:obj
  });
}
//修改轮播图
export function putCarousel(obj) {
  return fetch({
    // url: 'http://api.bangjiat.com/app/system/system/update/AppCarousel',
    url: 'http://api.bangjiat.com/app/system/carousel/update/AppCarousel',
    method: 'PUT',
    data:obj
  });
}
//删除轮播图
export function delCarousel(id) {
  const data = {
    carouselId:id+''
  };
  return fetch({
    // url: 'http://api.bangjiat.com/app/system/system/delete/AppCarousel',//+ getParams(data),
    url: 'http://api.bangjiat.com/app/system/carousel/delete/AppCarousel',
    method: 'delete',
    data
  });
}
