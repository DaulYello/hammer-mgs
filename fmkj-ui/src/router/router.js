import Main from '@/views/Main.vue';

// 不作为Main组件的子页面展示的页面单独写，如下
export const loginRouter = {
    path: '/login',
    name: 'login',
    meta: {
        title: 'Login - 登录'
    },
    component: () => import('@/views/login.vue')
};

export const page404 = {
    path: '/*',
    name: 'error-404',
    meta: {
        title: '404-页面不存在'
    },
    component: () => import('@/views/error-page/404.vue')
};

export const page403 = {
    path: '/403',
    meta: {
        title: '403-权限不足'
    },
    name: 'error-403',
    component: () => import('@//views/error-page/403.vue')
};

export const page500 = {
    path: '/500',
    meta: {
        title: '500-服务端错误'
    },
    name: 'error-500',
    component: () => import('@/views/error-page/500.vue')
};

export const preview = {
    path: '/preview',
    name: 'preview',
    component: () => import('@/views/form/article-publish/preview.vue')
};

export const locking = {
    path: '/locking',
    name: 'locking',
    component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')
};

// 作为Main组件的子页面展示但是不在左侧菜单显示的路由写在otherRouter里
export const otherRouter = {
    path: '/',
    name: 'otherRouter',
    redirect: '/home',
    component: Main,
    children: [
        { path: 'home', title: {i18n: 'home'}, name: 'home_index', component: () => import('@/views/home/home.vue') }
        ]
};

// 作为Main组件的子页面展示并且在左侧菜单显示的路由写在appRouter里
export const appRouter = [
    {
        path: '/user',
        icon: 'android-menu',
        title: '用户管理',
        name: 'user',
        component: Main,
        children: [
          {
              path: 'queryPage',
              icon: 'folder',
              name: 'queryPage',
              title: '用户信息查询',
              component: () => import('@/views/build-manage/user-manager.vue')
          },
            {
                path: 'realname',
                icon: 'folder',
                name: 'realname',
                title: '用户实名认证',
                component: () => import('@/views/build-manage/user-realname.vue')
            }
        ]
    },
    {
        path: '/race',
        icon: 'android-menu',
        title: '竞锤管理',
        name: 'race',
        component: Main,
        children: [
            /*{
                path: 'tiketManage',
                icon: 'folder',
                name: 'tiketManage',
                title: '门票管理',
                component: () => import('@/views/build-manage/tiket-manager.vue')
            },*/
            {
                path: 'activityManage',
                icon: 'folder',
                name: 'activityManage',
                title: '活动管理',
                component: () => import('@/views/activity-manage/activit-manager.vue')
            },
            {
                path: 'blackManage',
                icon: 'folder',
                name: 'blackManage',
                title: '黑名单设置',
                component: () => import('@/views/black-user/black-manager.vue')
            }
        ]
    },
    {
        path: '/system',
        icon: 'android-menu',
        title: '系统管理',
        name: 'system',
        component: Main,
        children: [
            {
                path: 'quartz',
                icon: 'folder',
                name: 'quartz',
                title: '定时任务',
                component: () => import('@/views/system/system-quartz.vue')
            },
            {
                path: 'quartzLog',
                icon: 'folder',
                name: 'quartzLog',
                title: '调度日志',
                component: () => import('@/views/system/quartz-log.vue')
            },
            {
                path: 'businessLog',
                icon: 'folder',
                name: 'businessLog',
                title: '业务操作日志',
                component: () => import('@/views/system/business-operations-log.vue')
            }
        ]
    },
    {
        path:'/configuration',
        icon:'android-menu',
        title:'系统配置',
        name:'configuration',
        component:Main,
        children:[
            {
                path:'addNotice',
                icon:'folder',
                name:'addNotice',
                title:'发布公告',
                component:()=>import('@/views/configurationSys/addNotice.vue')
            },
            {
                path:'noticeSys',
                icon:'folder',
                name:'noticeSys',
                title:'公告管理',
                component:()=>import('@/views/configurationSys/noticeSys.vue')
            },
            {
                path:'dataConfigurate',
                icon:'folder',
                name:'dataConfigurate',
                title:'数据字典配置',
                component:()=>import('@/views/configurationSys/noticeSys.vue')
            },
        ]
    }
];

// 所有上面定义的路由都要写在下面的routers里
export const routers = [
    loginRouter,
    otherRouter,
    preview,
    locking,
    ...appRouter,
    page500,
    page403,
    page404
];
