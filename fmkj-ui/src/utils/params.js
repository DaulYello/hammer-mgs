export function getParams(data) {
  let paramsArray = [];
  //拼接参数
  Object.keys(data).forEach(key => paramsArray.push(key + '=' + data[key]));
  let par = '';
  if (par.search(/\?/) === -1) {
      par += '?' + paramsArray.join('&')
  } else {
      par += '&' + paramsArray.join('&')
  }
  return par;
}
