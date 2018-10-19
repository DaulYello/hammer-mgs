import Cookies from 'js-cookie';
import {
  login,
  logout,
  getInfo,
  getMenus
} from 'api/login';
import {
  getUserInfo
} from 'api/user/user';
import {
  getToken,
  setToken,
  removeToken,
  getUid,
  setUid
} from 'utils/auth';

const user = {
    state: {
      //menus:undefined,
      token: getToken(),
      uid: getUid()
    },
    actions: {
      // 登录
      Login({
        commit
      }, userInfo) {
        console.info("login start = > ")
        const username = userInfo.username.trim();
        return new Promise((resolve, reject) => {
          login(username, userInfo.password).then(response => {
            const data = response;
            if (data.status === 200) {
              setToken(data.data.token);
              setUid(data.data.uid);
              Cookies.set('token', data.data.token);
              Cookies.set('user', data.data);
              Cookies.set("uid", data.data.uid);
              commit('SET_TOKEN', data.data.token);
              resolve();
            } else {
              this.$Message.error(data.message);
              Message({
                message: data.message,
                type: 'warning'
              });
              return Promise.reject('error');
            }
          }).catch(error => {
            reject(error);
          });
        });
      },

      GetMenus({
        commit
      }) {
        return new Promise((resolve, reject) => {
            commit('SET_MENUS',[]);
            resolve();
         // 这里的菜单如果是可配置的话可以去后台查询
         /*getMenus().then(response => {
            const data = response;
              console.log(data)
              if (data.status === 200) {
              commit('SET_MENUS',data.data);
              //commit('SET_MENUS', data.data);
              resolve();
            } else {
              this.$Message.error(data.message);
              Message({
                message: data.message,
                type: 'warning'
              });
              return Promise.reject('error');
            }
          }).catch(error => {
            console.log("加载菜单出现错误")
            reject(error);
          });*/
        });
      }
    },
    mutations: {
      // SET_MENUS: (state, menus) => {
      //   state.menus = menus;
      // },
      SET_TOKEN: (state, token) => {
        state.token = token;
      },
        logout (state, vm) {
            Cookies.remove('user');
            Cookies.remove('locked');
            Cookies.remove('menu');
            // 恢复默认样式
            let themeLink = document.querySelector('link[name="theme"]');
            themeLink.setAttribute('href', '');
            // 清空打开的页面等数据，但是保存主题数据
            let theme = '';
            if (localStorage.theme) {
                theme = localStorage.theme;
            }
            localStorage.clear();
            if (theme) {
                localStorage.theme = theme;
            }
        }
    }
};

export default user;
