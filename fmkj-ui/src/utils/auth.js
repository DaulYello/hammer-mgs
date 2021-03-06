import Cookies from 'js-cookie'

const TokenKey = 'X-Fmkj-Token'

const UidKey = 'uid'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getUid(){
  return Cookies.get(UidKey);
}
export function setUid(uid){
    return Cookies.get(UidKey,uid);
}
