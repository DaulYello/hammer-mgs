import fetch from 'utils/fetch';
import {
    getParams
} from 'utils/params';

export function login(username, password) {
  const data = {
    username,
    password
  };
  return fetch({
    url: '/backManger/fmkj/BmUser/login' + getParams(data),
    method: 'post'
  });
}

