import axios from 'axios';
import store from '../store';
import {
  getToken,getUid
} from 'utils/auth';

// 创建axios实例
const service = axios.create({
  //baseURL: process.env.BASE_API, // api的base_url
   baseURL: 'http://127.0.0.1:8080', // nmp run build 正式环境需要注释
   timeout: 5000 // 请求超时时间
});
// request拦截器
service.interceptors.request.use(config => {
    console.log('request is start =>')
  // Do something before request is sent
    console.log('store.getters.token =>'+store.getters.token)
    console.log('store.getters.token =>'+store.getters.uid)
  if (store.getters.token) {
    config.headers['X-Fmkj-token'] = getToken(); // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
    config.headers['uid'] = getUid();
  }
  return config;
}, error => {
  // Do something with request error
  console.log(error); // for debug
  Promise.reject(error);
})

// respone拦截器
service.interceptors.response.use(
    response => {
    /**
     * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
     * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
     */
    const res = response.data;
     console.info('respone is start==>')
    if (response.status === 401 || res.status === 40101) {
      // MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
      //   confirmButtonText: '重新登录',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // }).then(() => {
      //   store.dispatch('FedLogOut').then(() => {
      //     location.reload(); // 为了重新实例化vue-router对象 避免bug
      //   });
      // })
       return Promise.reject('error');
    }
    if (res.status === 40301) {
      // Message({
      //   message: '当前用户无相关操作权限！',
      //   type: 'error',
      //   duration: 5 * 1000
      // });
       return Promise.reject('error');
    }
    if (response.status !== 200 && res.status !== 200) {
    //   Message({
    //     message: res.message,
    //     type: 'error',
    //     duration: 5 * 1000
    //   });
     } else {
       return response.data;
     }
  },
  error => {
    console.log('err' + error); // for debug
    // Message({
    //   message: error.message,
    //   type: 'error',
    //   duration: 5 * 1000
    // });
    return Promise.reject(error);
  }
);

export default service;
