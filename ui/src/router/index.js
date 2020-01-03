import Vue from 'vue';
import VueRouter from 'vue-router';
import Layout from '../layout/Layout';

//首页
import home from '../page/home/home';

import invokeapilog from '../page/log/invokeapilog';



//操作日志
import syslog from '../page/log/syslog';

import sysGenerator from '../page/code/sysGenerator';
import sysMenu from '../page/log/sysmenu';
Vue.use(VueRouter);

const router = new VueRouter({
    //mode: 'history',
    scrollBehavior: () => ({y: 0}),
    routes: [
        {
            name: '',
            path: '/',
            redirect: '/home',
            hidden: true
        },
        {
            name: 'home',
            path: '/home',
            component: Layout,
            redirect: '/home',
            meta: { title: '首页'},
            children: [
                {
                    name: 'home',
                    path: '/home',
                    component: home,
                    meta: { title: '首页'}
                }
            ]
        },

        {
            name: '日志管理',
            path: '/log',
            component: Layout,
            redirect: '/log',
            meta: { title: '日志管理'},
            children: [
                {
                    name: 'syslog',
                    path: '/syslog',
                    component: syslog,
                    meta: { title: '操作日志'}
                }
            ]
        },{
            name: '代码管理',
            path: '/codeManage',
            component: Layout,
            redirect: '/codeManage',
            meta: { title: '代码管理'},
            children: [
                {
                    name: 'sysGenerator',
                    path: '/sysGenerator',
                    component: sysGenerator,
                    meta: { title: '代码生成'}
                }
            ]
        },
    ]
});

export default router;
